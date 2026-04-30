package com.example.noteswithregistration.system.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.noteswithregistration.R
import com.example.noteswithregistration.domain.model.Task
import com.example.noteswithregistration.presentation.uistate.EditTaskUIState
import com.example.noteswithregistration.presentation.viewmodels.EditTaskScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun EditTaskScreen(
    viewModel: EditTaskScreenViewModel = koinViewModel(),
    onNavigateSave: () -> Unit,
    onNavigateBack: () -> Unit,
) { // comment, testing Ktlint
    val uiState by viewModel.editTaskUIState.collectAsStateWithLifecycle()
    LaunchedEffect(uiState) {
        if (uiState is EditTaskUIState.Empty) {
            onNavigateBack()
        }
    }
    when (uiState) {
        is EditTaskUIState.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier.fillMaxSize(),
            )
        }

        is EditTaskUIState.Loaded -> {
            val task = (uiState as EditTaskUIState.Loaded).task
            EditTaskContent(
                task = task,
                onSaveChanges = {
                    viewModel.updateTask(it)
                    onNavigateSave()
                },
            )
        }
        else -> {}
    }
}

@Composable
private fun EditTaskContent(
    task: Task,
    onSaveChanges: (Task) -> Unit,
) {
    var title by remember(task.id) { mutableStateOf(task.title) }
    var description by remember(task.id) { mutableStateOf(task.description) }
    val scrollState = rememberScrollState()
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Card(modifier = Modifier.fillMaxWidth()) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = title,
                onValueChange = {
                    title = it
                },
            )
        }
        Card {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = description,
                onValueChange = {
                    description = it
                },
            )
        }
        Button(
            onClick = {
                onSaveChanges(task.copy(title = title, description = description))
            },
        ) {
            Text(stringResource(R.string.Save))
        }
    }
}
