package com.example.noteswithregistration.screens

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun NewTask(viewModel: MainViewModel) {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TaskField(
            placeholder = "NewTask",
            text = viewModel.text,
            onValueChange = { viewModel.text = it }
        )
        Box {
            TaskField(
                modifier = Modifier.fillMaxSize(),
                text = viewModel.description,
                placeholder = "Description",
                onValueChange = { viewModel.description = it }
            )
            IconButton(
                onClick = {
                    viewModel.taskValidationCheck(
                        context,
                        successfulMessage = "Task added",
                        unsuccessfulMessage = "title/description cant be empty"
                    )

                }, modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(10.dp)
            ) {
                Icon(
                    painterResource(com.example.noteswithregistration.R.drawable.outline_check_24),
                    contentDescription = "Save",
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }
}

@Composable
fun TaskField(
    text: String,
    modifier: Modifier = Modifier,
    placeholder: String,
    onValueChange: (String) -> Unit = {}
) {
    TextField(
        modifier = modifier.fillMaxWidth(),
        value = text,
        onValueChange = {
            onValueChange(it)
        },
        placeholder = { Text(placeholder) }
    )
}

@Preview
@Composable
fun NewTaskPreview() {
    NewTask(viewModel = viewModel())
}