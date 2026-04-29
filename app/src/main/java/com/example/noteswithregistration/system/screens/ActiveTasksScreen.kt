package com.example.noteswithregistration.system.screens

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
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.noteswithregistration.R
import com.example.noteswithregistration.domain.model.Task
import com.example.noteswithregistration.presentation.viewmodels.ActiveTaskScreenViewModel
import com.example.noteswithregistration.system.theme.AppTypography
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun ActiveTasksScreen(
    viewModel: ActiveTaskScreenViewModel = koinViewModel(),
    onNavigateToEdit: (taskId: Int) -> Unit
) {
    val tasks by viewModel.activeTasks.collectAsStateWithLifecycle()
    ActiveTaskContent(
        tasks = tasks,
        onNavigateToEdit = onNavigateToEdit,
        onDelete = { viewModel.deleteTask(it) }
    )
}

@Composable
private fun ActiveTaskContent(
    tasks: List<Task>,
    onNavigateToEdit: (taskId: Int) -> Unit,
    onDelete: (task: Task) -> Unit
) {
    if (tasks.isEmpty()) {
        Text(stringResource(R.string.no_active_tasks), modifier = Modifier.padding(16.dp))
        return
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        items(tasks, key = { it.id }) { task ->
            SelectableTaskItem(
                task = task,
                onNavigationToEdit = {
                    onNavigateToEdit(task.id)
                },
                onDelete = { onDelete(task) },
            )
        }
    }

}

@Composable
fun ActiveTaskText(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle,
    textAlign: TextAlign = TextAlign.Start,
    maxLines: Int,
    overflow: TextOverflow = TextOverflow.Ellipsis
) {
    Text(
        text = text,
        modifier = modifier,
        overflow = overflow,
        style = style,
        textAlign = textAlign,
        maxLines = maxLines
    )
}

@Composable
fun SelectableTaskItem(
    task: Task,
    onNavigationToEdit: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var expanded by remember(task.id) { mutableStateOf(false) }
    val titleStyle = if (expanded) AppTypography.titleLarge else AppTypography.titleMedium
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
                    onNavigationToEdit()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = stringResource(R.string.edit)
                )
            }
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ActiveTaskText(
                    text = task.title,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    style = titleStyle,
                    maxLines = if (expanded) Int.MAX_VALUE else 1
                )

                AnimatedVisibility(expanded) {
                    ActiveTaskText(
                        text = task.description,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        style = AppTypography.bodyMedium,
                        maxLines = Int.MAX_VALUE
                    )
                }
            }
            IconButton(
                onClick = onDelete,
                modifier = Modifier
            ) {
                Icon(
                    painterResource(R.drawable.sharp_delete_24),
                    tint = Color.Red,
                    contentDescription = stringResource(R.string.delete)
                )
            }
        }

    }
}


@Preview
@Composable
fun ActiveTaskItemPreview() {
    SelectableTaskItem(
        task = Task(1, "Task1", "Description1"),
        onNavigationToEdit = {},
        onDelete = {},
    )
}
