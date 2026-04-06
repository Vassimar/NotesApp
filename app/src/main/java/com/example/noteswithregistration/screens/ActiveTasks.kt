package com.example.noteswithregistration.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.noteswithregistration.R
import com.example.noteswithregistration.ui.theme.AppTypography

@Composable
fun ActiveTasks(viewModel: MainViewModel) {
    val tasks = viewModel.tasks.filter { it.isActive }

    if (tasks.isEmpty()) {
        Text("No Active Tasks", modifier = Modifier.padding(16.dp))
        return
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        items(tasks) { task ->
            val isEdited = viewModel.editTracker == task.id
            TaskItem(
                task = task, isEdited = isEdited, onEditToggle = {
                    viewModel.editTracker = if (isEdited) null else task.id
                }, onDelete = { viewModel.deleteTask(task) }, viewModel = viewModel
            )
        }
    }
}

@Composable
fun TaskText(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(text = text, modifier = modifier, style = style, textAlign = textAlign)
}

@Composable
fun TaskItem(
    task: Task,
    isEdited: Boolean,
    onEditToggle: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
) {
    var expanded by remember { mutableStateOf(false) }
    var titleStyle by remember { mutableStateOf(AppTypography.titleMedium) }
    var titleAlign by remember { mutableStateOf(TextAlign.Start) }
    var text by remember { mutableStateOf(task.title) }
    var description by remember { mutableStateOf(task.description) }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                expanded = !expanded
                titleStyle = if (expanded) AppTypography.titleLarge else AppTypography.titleMedium
                titleAlign = if (expanded) TextAlign.Center else TextAlign.Start
            }, verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {
            onEditToggle()
            if (expanded) {
                viewModel.updateTask(task, text, description)
            } else {
                expanded = true
            }
        }) {
            Icon(
                imageVector = if (isEdited) Icons.Default.Check else Icons.Default.Edit,
                contentDescription = if (isEdited) "Save" else "Edit"
            )
        }
        Column(modifier = Modifier.weight(1f)) {
            if (isEdited) {
                TextField(
                    value = text,
                    onValueChange = { text = it },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    )
                )
            } else {
                TaskText(
                    text = task.title,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    style = titleStyle,
                    textAlign = titleAlign
                )
            }
            AnimatedVisibility(expanded) {
                if (isEdited) {
                    TextField(
                        value = description,
                        onValueChange = { description = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding( 16.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        )
                    )
                } else {
                    TaskText(
                        text = task.description,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        style = AppTypography.bodyMedium
                    )
                }
            }
        }
        IconButton(onClick = onDelete) {
            Icon(
                painterResource(R.drawable.sharp_delete_24),
                tint = Color.Red,
                contentDescription = "Delete"
            )
        }
    }
    Spacer(modifier = Modifier.width(3.dp))
}

@Preview
@Composable
fun TaskItemPreview() {

    TaskItem(
        task = Task(1, "Task1", "Description1"),
        isEdited = true,
        onEditToggle = {},
        onDelete = {},
        viewModel = viewModel()
    )
}



