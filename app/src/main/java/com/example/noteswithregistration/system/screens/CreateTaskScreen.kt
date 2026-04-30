package com.example.noteswithregistration.system.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.noteswithregistration.R
import com.example.noteswithregistration.presentation.viewmodels.CreateTaskViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun CreateTaskScreen(viewModel: CreateTaskViewModel = koinViewModel()) {
    val context = LocalContext.current
    val toastMessage = stringResource(R.string.please_fill_all_fields)
    LaunchedEffect(viewModel.showError) {
        if (viewModel.showError) {
            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
        }
    }
    NewTaskContent(
        onAddTask = { title, description ->
            viewModel.addTask(title, description)
        },
    )
}

@Composable
private fun NewTaskField(
    text: String,
    modifier: Modifier = Modifier,
    placeholder: String,
    onValueChange: (String) -> Unit = {},
) {
    TextField(
        modifier = modifier.fillMaxWidth(),
        value = text,
        onValueChange = {
            onValueChange(it)
        },
        placeholder = { Text(placeholder) },
    )
}

@Composable
private fun NewTaskContent(onAddTask: (title: String, description: String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        var text by remember { mutableStateOf("") }
        var description by remember { mutableStateOf("") }
        NewTaskField(
            placeholder = stringResource(R.string.new_task),
            text = text,
            onValueChange = { text = it },
        )
        Box {
            NewTaskField(
                modifier = Modifier.fillMaxSize(),
                text = description,
                placeholder = stringResource(R.string.description),
                onValueChange = { description = it },
            )
            IconButton(
                onClick = {
                    onAddTask(text, description)
                },
                modifier =
                    Modifier
                        .align(Alignment.BottomEnd)
                        .padding(10.dp),
            ) {
                Icon(
                    painterResource(R.drawable.outline_check_24),
                    contentDescription = stringResource(R.string.Save),
                    modifier = Modifier.size(40.dp),
                )
            }
        }
    }
}

@Preview
@Composable
private fun NewTaskPreview() {
    NewTaskContent(
        onAddTask = { _, _ -> },
    )
}
