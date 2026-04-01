package com.example.noteswithregistration.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.noteswithregistration.R
import com.example.noteswithregistration.ui.theme.AppTypography

@Composable
fun ActiveTasks(viewModel: MainViewModel) {
    val tasks = viewModel.tasks
    var titleStyle = AppTypography.titleMedium
    var titleAlign = TextAlign.Start
    if (tasks.any { it.isActive }) {
        val activeTasks = tasks.filter { it.isActive }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            items(activeTasks) { task ->
                var switch by remember { mutableStateOf(false) }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            switch = !switch

                            titleStyle = if (switch) {
                                AppTypography.titleLarge
                            } else {
                                AppTypography.titleMedium
                            }

                            titleAlign = if (switch) {
                                TextAlign.Center
                            } else {
                                TextAlign.Start
                            }
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = task.title,
                        modifier = Modifier.weight(1f),
                        style = titleStyle,
                        textAlign = titleAlign
                    )
                    IconButton(
                        onClick = { viewModel.deleteTask(task) }) {
                        Icon(
                            painterResource(R.drawable.sharp_delete_24),
                            tint = Color.Red,
                            contentDescription = "Delete",
                        )
                    }
                }
                AnimatedVisibility(switch) {
                    Text(
                        task.description,
                        modifier = Modifier.padding(8.dp),
                        style = AppTypography.bodyMedium
                    )
                }
                Spacer(modifier = Modifier.width(3.dp))
            }
        }
    } else {
        Text(text = "No Active Tasks", modifier = Modifier.padding(10.dp))
    }
}

@Composable
private fun Content() {
    val tasks = mutableListOf(
        Task("project", "Task1", true), Task("demon", "Task2", true)
    )
    var titleStyle = AppTypography.titleMedium
    if (tasks.any { it.isActive }) {
        val activeTasks = tasks.filter { it.isActive }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            items(activeTasks) { task ->
                var switch by remember { mutableStateOf(false) }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            switch = !switch
                            titleStyle = if (switch) {
                                AppTypography.titleLarge
                            } else {
                                AppTypography.titleMedium
                            }
                        }, verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = task.title,
                        modifier = Modifier.weight(1f),
                        style = titleStyle,
                    )
                    IconButton(
                        onClick = { tasks.remove(task) }) {
                        Icon(
                            painterResource(R.drawable.sharp_delete_24),
                            tint = Color.Red,
                            contentDescription = "Delete",
                        )
                    }

                }
                AnimatedVisibility(switch) {
                    Text(
                        task.description,
                        modifier = Modifier.padding(8.dp),
                        style = AppTypography.bodyMedium
                    )
                }
                Spacer(modifier = Modifier.width(3.dp))
            }
        }
    } else {
        Text(text = "No Active Tasks", modifier = Modifier.padding(10.dp))
    }
}

@Preview
@Composable
fun ActiveTaskPreview() {
    Content()


}



