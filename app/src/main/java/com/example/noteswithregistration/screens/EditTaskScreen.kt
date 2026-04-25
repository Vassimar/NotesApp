package com.example.noteswithregistration.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import androidx.navigation.NavController
import com.example.noteswithregistration.R

@Composable

fun EditTaskScreen(viewModel: MainViewModel, taskId: Int, navController: NavController) {
    LaunchedEffect(taskId) {
        viewModel.loadTask(taskId)
    }
    val task by viewModel.editTask.collectAsStateWithLifecycle()
    task?.let { task ->
        var title by remember(task.id) { mutableStateOf(task.title) }
        var description by remember(task.id) { mutableStateOf(task.description) }
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)

        ) {
            Card (modifier = Modifier.fillMaxWidth()){
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = title,
                    onValueChange = {
                        title = it
                    }
                )
            }
            Card {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = description,
                    onValueChange = {
                        description = it
                    }
                )
            }
            Button(
                onClick = {
                    viewModel.updateTask(task.copy(title = title, description = description))
                    navController.popBackStack()
                }
            ) {
                Text(stringResource(R.string.Save))
            }
        }
    }
}
