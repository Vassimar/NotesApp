package com.example.noteswithregistration.screens

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
    val toast = Toast.makeText(context, "Task added", Toast.LENGTH_SHORT)
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
                    /* move to viewmodel*/
                    if (viewModel.text.isEmpty() || viewModel.description.isEmpty()) {
                        toast.setText("Task name/description cannot be empty")
                        toast.show()
                        return@IconButton
                    } else {
                        val task = Task(title = viewModel.text, description = viewModel.description)
                        viewModel.addTask(task)
                        viewModel.text = ""
                        viewModel.description = ""
                        toast.show()
                    }
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
        placeholder = { Text(placeholder) },
        singleLine = false
    )
}

@Preview
@Composable
fun NewTaskPreview() {
    NewTask(viewModel = viewModel())
}



