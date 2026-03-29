package com.example.noteswithregistration.screens

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel


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