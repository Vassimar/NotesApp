package com.example.noteswithregistration.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


class MainViewModel : ViewModel() {
    var text by  mutableStateOf("")

    var description by   mutableStateOf("")
    var tasks by mutableStateOf(listOf<Task>())
    fun addTask(task: Task) {
        tasks = tasks + task
    }

    fun toggleTask(task: Task) {
        tasks = tasks.map {
            if (it == task) it.copy(isActive = !it.isActive)
            else it
        }
    }


}


sealed class Routes(val route: String) {
    object MainScreen : Routes("main_screen")
    object TasksScreen : Routes("tasks_screen")
    object NewTaskScreen : Routes("new_Task_screen")
}

data class Task(val title: String, val description: String, var isActive: Boolean = false)