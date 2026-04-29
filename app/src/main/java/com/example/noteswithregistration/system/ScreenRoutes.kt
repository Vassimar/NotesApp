package com.example.noteswithregistration.system

sealed class Routes(val route: String) {
    object MainScreen : Routes("main_screen")
    object TasksScreen : Routes("tasks_screen")
    object NewTaskScreen : Routes("new_tasks_screen")
    object EditTaskScreen : Routes("editTask/{taskId}") {
        fun withArgs(taskId: Int): String {
            return "editTask/$taskId"
        }
    }
}