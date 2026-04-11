package com.example.noteswithregistration.db

class TaskRepository(private val taskDao: TaskDao) {
    fun getAllTasks() = taskDao.getAll()
    fun getActiveTasks() = taskDao.getActive()
    suspend fun insertTask(task: TaskEntity) = taskDao.insert(task)
    suspend fun updateTask(task: TaskEntity) = taskDao.update(task)
    suspend fun deleteTask(task: TaskEntity) = taskDao.delete(task)

}