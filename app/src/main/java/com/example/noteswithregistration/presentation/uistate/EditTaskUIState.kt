package com.example.noteswithregistration.presentation.uistate

import com.example.noteswithregistration.domain.model.Task

sealed class EditTaskUIState {
    data class Loaded(val task: Task) : EditTaskUIState()
    object Loading : EditTaskUIState()
    object Empty: EditTaskUIState()
}