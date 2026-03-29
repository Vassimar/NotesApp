package com.example.noteswithregistration.ui.theme.screens

import android.R
import android.R.attr.label
import android.R.attr.textColor
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.backgroundDark
import com.example.compose.primaryDark

@Composable
fun NewTask(viewModel: MainViewModel) {
    var text by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()


    ) {
        TaskField(placeholder = "NewTask", text = text, onValueChange = { text = it })
        Box() {
            TaskField(
                modifier = Modifier.fillMaxSize(),
                text = description,
                placeholder = "Description",
                onValueChange = {description = it })

            IconButton(
                onClick = {
                    val task = Task(title = text, description = description)
                    viewModel.addTask(task)
                    text = ""
                    description = ""

                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(10.dp)
            ) {
                Icon(
                    painterResource(com.example.noteswithregistration.R.drawable.outline_check_24),
                    contentDescription = "Save",
                    tint = primaryDark,
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
        placeholder = { Text(placeholder, color = primaryDark) },
        colors = TextFieldDefaults.colors(
            focusedTextColor = primaryDark,
            unfocusedTextColor = primaryDark,
            focusedContainerColor = backgroundDark,
            unfocusedContainerColor = backgroundDark
        )
    )


}

@Preview
@Composable
fun NewTaskPreview() {
    NewTask(viewModel = viewModel())
}



