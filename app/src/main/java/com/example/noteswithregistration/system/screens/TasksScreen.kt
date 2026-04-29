package com.example.noteswithregistration.system.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.noteswithregistration.domain.model.Task
import com.example.noteswithregistration.presentation.viewmodels.TasksScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun TasksScreen(viewModel: TasksScreenViewModel = koinViewModel()) {
    val tasks by viewModel.allTasks.collectAsStateWithLifecycle()
    TasksScreenContent(
        tasks = tasks,
        onTaskClick = {
            viewModel.toggleTask(it)
        }
    )
}

@Composable
private fun ToggleTaskItem(
    task: Task,
    onTaskClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onTaskClick()
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically

    ) {
        Text(
            text = task.title,
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        )
        RadioButton(
            selected = task.isActive,
            onClick = {
                onTaskClick()
            }
        )
    }
    HorizontalDivider(
        thickness = 1.dp,
    )
}

@Composable
private fun TasksScreenContent(
    tasks: List<Task>,
    onTaskClick: (task: Task) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {
        items(tasks, key = { it.id }) { task ->
            ToggleTaskItem(
                task = task,
                onTaskClick = {
                    onTaskClick(task)
                }
            )
        }
    }
}

@Preview
@Composable
fun TasksScreenPreview() {
    TasksScreenContent(
        tasks = listOf(
            Task(1, "Task1", "Description1"),
            Task(2, "Task2", "Description2"),
        ),
        onTaskClick = {}
    )
}