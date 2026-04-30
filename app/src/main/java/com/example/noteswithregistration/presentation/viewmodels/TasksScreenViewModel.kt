package com.example.noteswithregistration.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteswithregistration.domain.model.Task
import com.example.noteswithregistration.domain.repository.TaskRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TasksScreenViewModel(
    private val repository: TaskRepository,
) : ViewModel() {
    val allTasks =
        repository.observeAllTasks().stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList(),
        )

    fun toggleTask(task: Task) {
        viewModelScope.launch {
            repository.updateTask(task.copy(isActive = !task.isActive))
        }
    }
}
