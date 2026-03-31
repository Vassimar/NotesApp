package com.example.noteswithregistration.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ActiveTasks(viewModel: MainViewModel) {
    val tasks = viewModel.tasks
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Yellow)
            .padding(4.dp)
    ) {
        items(tasks) { task ->
            var switch by remember { mutableStateOf(false) }
            if (task.isActive) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Red)
                        .padding(8.dp)
                        .clickable {
                            switch = !switch
                        }
                ) {
                    Text(
                        text = if (switch) task.description else task.title,
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(modifier = Modifier.width(3.dp))
            } else {
                Text(text = "No Active Tasks", modifier = Modifier.padding(10.dp))
            }
        }
    }
}



