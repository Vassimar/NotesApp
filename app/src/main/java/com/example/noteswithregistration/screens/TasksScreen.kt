package com.example.noteswithregistration.screens

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.noteswithregistration.db.TaskEntity

@Composable
fun TasksScreen(viewModel: MainViewModel) {
    val tasks by viewModel.allTasks.collectAsStateWithLifecycle()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {
        items(tasks, key = {it.id}) { task ->
            SelectableTaskItem(
                task = task,
                onTaskClick = {
                    viewModel.toggleTask(task)
                },

            )
        }

    }
}

@Composable
fun SelectableTaskItem(
    task: TaskEntity,
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
private fun Content(
    tasks: List<TaskEntity>,
    onTaskClick: (task: TaskEntity) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {
        items(tasks) { task ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        onTaskClick(task)
                    },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {
                Text(text = task.title, color = Color.Yellow)
                RadioButton(
                    selected = task.isActive,
                    onClick = {
                        onTaskClick(task)
                    }
                )
            }
            HorizontalDivider(
                thickness = 1.dp,
            )
        }
    }
}

@Preview
@Composable
fun TasksScreenPreview() {
    Content(
        tasks = listOf(
            TaskEntity(1, "Task1", "Description1"),
            TaskEntity(2, "Task2", "Description2"),
        ),
        onTaskClick = {}
    )
}