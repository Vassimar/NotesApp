package com.example.noteswithregistration.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var editTracker by mutableStateOf<Int?>(null)
    var text by mutableStateOf("")
    var count by mutableIntStateOf(0)
    var description by mutableStateOf("")
    var tasks by mutableStateOf(listOf<Task>())
    fun addTask(task: Task) {
        tasks = tasks + task
    }
    fun toggleTask(task: Task) {
        tasks = tasks.map {
            if (it == task) it.copy(isActive = !it.isActive)
            else it
        }
    }
    fun deleteTask(task: Task) {
        tasks = tasks - task
    }
    fun updateTask(task: Task,title:String,description:String){
        tasks = tasks.map {
            if (it.id == task.id) it.copy(title = title, description = description)
            else it
        }
    }
}

sealed class Routes(val route: String) {
    object MainScreen : Routes("main_screen")
    object TasksScreen : Routes("tasks_screen")
    object NewTaskScreen : Routes("new_Task_screen")
}

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val isActive: Boolean = false
)