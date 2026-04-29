package com.example.noteswithregistration.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteswithregistration.domain.model.Task
import com.example.noteswithregistration.domain.repository.TaskRepository
import kotlinx.coroutines.launch

class CreateTaskViewModel(
    private val repository: TaskRepository
) : ViewModel() {
    var showError by mutableStateOf(false)
        private set

    fun addTask(title: String, description: String) {
        if (title.isBlank() || description.isBlank()) {
            showError = true
            return
        }
        showError = false
        viewModelScope.launch {
            repository.addTask(Task(title = title, description = description))
        }
    }
}
