package com.example.noteswithregistration.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.backgroundDark
import com.example.compose.primaryDark

@Composable
fun TasksScreen(viewModel: MainViewModel) {
    val tasks = viewModel.tasks
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
                item {
                    Column(modifier = Modifier.clickable{
                        task.isActive = !task.isActive
                    })
                     {
                        Text(text = task.title, color = primaryDark)
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

@Preview
@Composable
fun TasksScreenPreview() {
    TasksScreen(viewModel = viewModel())
}

