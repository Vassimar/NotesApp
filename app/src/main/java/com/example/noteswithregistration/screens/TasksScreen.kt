package com.example.noteswithregistration.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TasksScreen(viewModel: MainViewModel) {
    val tasks = viewModel.tasks
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
                        viewModel.toggleTask(task)
                    },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {
                Text(text = task.title)
                RadioButton(
                    selected = task.isActive,
                    onClick = {
                        viewModel.toggleTask(task)
                    }
                )
            }
            HorizontalDivider(
                thickness = 1.dp,
            )
        }
    }
}

@Composable
private fun Content(
    tasks: List<Task>,
    OnTaskClick: (task: Task) -> Unit,
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
                        OnTaskClick(task)
                    },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {
                Text(text = task.title, color = Color.Yellow)
                RadioButton(
                    selected = task.isActive,
                    onClick = {
                        OnTaskClick(task)
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
    content(
        tasks = listOf(
            Task("Task1", "Description1"),
            Task("Task2", "Description2"),
        ),
        OnTaskClick = {}
    )
}

