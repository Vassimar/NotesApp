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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.noteswithregistration.db.TaskEntity

@Composable
fun NewTask(viewModel: MainViewModel) {
    val context = LocalContext.current
    var text by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TaskField(
            placeholder = "NewTask",
            text = text,
            onValueChange = { text = it }
        )
        Box {
            TaskField(
                modifier = Modifier.fillMaxSize(),
                text = description,
                placeholder = "Description",
                onValueChange = { description = it }
            )
            IconButton(
                onClick = {
                    if (text.isBlank() || description.isBlank()) {
                        Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                    } else {
                        val task = TaskEntity(title = text, description = description)
                        viewModel.addTask(task)
                        text = ""
                        description = ""
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
        placeholder = { Text(placeholder) }
    )
}

@Preview
@Composable
fun NewTaskPreview() {
    NewTask(viewModel = viewModel())
}