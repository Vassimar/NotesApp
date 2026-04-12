package com.example.noteswithregistration.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.noteswithregistration.R
import com.example.noteswithregistration.db.TaskEntity
import com.example.noteswithregistration.ui.theme.AppTypography

@Composable
fun ActiveTasks(viewModel: MainViewModel) {
    val tasks by viewModel.activeTasks.collectAsStateWithLifecycle()

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
                task = task, isEdited = isEdited,
                onEditToggle = {
                    viewModel.editTracker = if (isEdited) null else task.id

                },
                onDelete = { viewModel.deleteTask(task) },
                onUpdate = { viewModel.updateTask(task.copy(title = it.title, description = it.description))}
            )
        }
    }
}

@Composable
fun TaskText(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle,
    textAlign: TextAlign = TextAlign.Start,
    maxLines: Int,
    overflow: TextOverflow = TextOverflow.Ellipsis
) {
    Text(text = text,
        modifier = modifier,
        overflow = overflow,
        style = style,
        textAlign = textAlign,
        maxLines = maxLines)
}

@Composable
fun TaskItem(
    task: TaskEntity,
    isEdited: Boolean,
    onEditToggle: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier,
    onUpdate: (task: TaskEntity) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    val titleStyle = if (expanded) AppTypography.titleLarge else AppTypography.titleMedium
    var text by remember { mutableStateOf(task.title) }
    var description by remember { mutableStateOf(task.description) }
    OutlinedCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                expanded = !expanded
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = CardDefaults.elevatedShape
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    if (isEdited) {
                        onUpdate(task.copy(title = text, description = description))
                    }
                    onEditToggle()
                },
                modifier = Modifier
                    .padding(end = 16.dp)
            ) {
                Icon(
                    imageVector = if (isEdited) Icons.Default.Check else Icons.Default.Edit,
                    contentDescription = if (isEdited) "Save" else "Edit"
                )
            }
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (isEdited) {
                    TextField(
                        value = text,
                        onValueChange = { text = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
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
                        maxLines = if (expanded) Int.MAX_VALUE else 1
                    )
                }
                AnimatedVisibility(isEdited || expanded) {
                    if (isEdited) {
                        TextField(
                            value = description,
                            onValueChange = { description = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                disabledContainerColor = Color.Transparent,
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
                            style = AppTypography.bodyMedium,
                           maxLines = Int.MAX_VALUE
                        )
                    }
                }
            }
            IconButton(
                onClick = onDelete,
                modifier = Modifier
            ) {
                Icon(
                    painterResource(R.drawable.sharp_delete_24),
                    tint = Color.Red,
                    contentDescription = "Delete"
                )
            }
        }
    }
}

@Preview
@Composable
fun TaskItemPreview() {
    TaskItem(
        task = TaskEntity(1, "Task1", "Description1"),
        isEdited = true,
        onEditToggle = {},
        onDelete = {},
        onUpdate = {}
    )
}
