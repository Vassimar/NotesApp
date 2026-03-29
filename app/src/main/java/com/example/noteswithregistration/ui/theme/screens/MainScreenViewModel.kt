package com.example.noteswithregistration.ui.theme.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.rememberNavController


class MainViewModel(): ViewModel() {
var tasks = mutableStateListOf<Task>()
    private set
    fun addTask(task:Task){
        tasks.add(task)
    }
}




sealed class Routes (val route:String){
    object MainScreen : Routes("main_screen")
    object TasksScreen : Routes("tasks_screen")
    object NewTaskScreen:Routes("new_Task_screen")
}
data class Task(val title: String, val description: String, var isActive: Boolean = false)