package com.example.noteswithregistration

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.compose.backgroundDark
import com.example.compose.primaryDark
import com.example.noteswithregistration.ui.theme.screens.MainViewModel

@Composable
fun ActiveTasks(viewModel: MainViewModel) {
    val tasks = viewModel.tasks
    var switcher by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundDark)
    )
    {
        LazyColumn(
            modifier = Modifier
                .background(backgroundDark)
                .padding(4.dp)
        ) {
            tasks.forEach { task ->
                if (task.isActive) {

                    item {
                        Column(modifier = Modifier.clickable {
                            switcher = !switcher

                        }) {
                            Text(
                                text = if (switcher) task.description else task.title,
                                color = primaryDark
                            )
                            HorizontalDivider(
                                Modifier,
                                thickness = 1.dp,
                                color = primaryDark
                            )
                        }
                    }
                }
            }
        }
    }
}