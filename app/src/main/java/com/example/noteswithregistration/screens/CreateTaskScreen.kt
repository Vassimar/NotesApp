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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.noteswithregistration.R
import com.example.noteswithregistration.db.TaskEntity

@Composable
fun CreateTaskScreen(viewModel: MainViewModel) {
    val context = LocalContext.current
    var text by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val toastMessage = stringResource(R.string.please_fill_all_fields)
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        NewTaskField(
            placeholder = stringResource(R.string.new_task),
            text = text,
            onValueChange = { text = it }
        )
        Box {
            NewTaskField(
                modifier = Modifier.fillMaxSize(),
                text = description,
                placeholder = stringResource(R.string.description),
                onValueChange = { description = it }
            )
            IconButton(
                onClick = {
                    if (text.isBlank() || description.isBlank()) {
                        Toast.makeText(
                            context,
                            toastMessage,
                            Toast.LENGTH_SHORT
                        ).show()
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
                    painterResource(R.drawable.outline_check_24),
                    contentDescription = stringResource(R.string.Save),
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }
}

@Composable
fun NewTaskField(
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

@Composable
fun NewTaskContent() {
    Column(
        Modifier.fillMaxSize(),
    ) {
        NewTaskField(
            placeholder = "NewTask",
            text = "preview",
            onValueChange = {}
        )
        NewTaskField(
            placeholder = "Description",
            text = "preview description",
            onValueChange = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview
@Composable
fun NewTaskPreview() {
    NewTaskContent()
}
