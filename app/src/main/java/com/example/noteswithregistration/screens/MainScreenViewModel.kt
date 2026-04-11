package com.example.noteswithregistration.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteswithregistration.db.TaskEntity
import com.example.noteswithregistration.db.TaskRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: TaskRepository
) : ViewModel() {
    val allTasks = repository.getAllTasks().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )
    val activeTasks = repository.getActiveTasks().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    fun addTask(task: TaskEntity) {
        viewModelScope.launch {
            repository.insertTask(task)
        }
    }

    fun updateTask(task: TaskEntity) {
        viewModelScope.launch {
            repository.updateTask(task)
        }
    }

    fun deleteTask(task: TaskEntity) {
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }

    fun toggleTask(task: TaskEntity) {
        viewModelScope.launch {
            repository.updateTask(task.copy(isActive = !task.isActive))
        }
    }

    var editTracker by mutableStateOf<Int?>(null)
}

sealed class Routes(val route: String) {
    object MainScreen : Routes("main_screen")
    object TasksScreen : Routes("tasks_screen")
    object NewTaskScreen : Routes("new_tasks_screen")
}