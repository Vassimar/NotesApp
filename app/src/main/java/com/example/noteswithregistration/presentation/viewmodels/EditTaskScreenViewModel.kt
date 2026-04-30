package com.example.noteswithregistration.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteswithregistration.domain.model.Task
import com.example.noteswithregistration.domain.repository.TaskRepository
import com.example.noteswithregistration.presentation.uistate.EditTaskUIState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class EditTaskScreenViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: TaskRepository,
) : ViewModel() {
    fun updateTask(task: Task) {
        viewModelScope.launch {
            repository.updateTask(task)
        }
    }

    private val taskId: Int = savedStateHandle["taskId"]!!

    @OptIn(ExperimentalCoroutinesApi::class)
    val editTaskUIState =
        repository.observeTaskById(taskId)
            .map { task ->
                if (task == null) {
                    EditTaskUIState.Empty
                } else {
                    EditTaskUIState.Loaded(task)
                }
            }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                EditTaskUIState.Loading,
            )
}
