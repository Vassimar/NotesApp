package com.example.noteswithregistration.domain.repository

import com.example.noteswithregistration.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun observeActiveTasks(): Flow<List<Task>>

    fun observeAllTasks(): Flow<List<Task>>

    fun observeTaskById(id: Int): Flow<Task?>

    suspend fun addTask(task: Task)

    suspend fun updateTask(task: Task)

    suspend fun deleteTask(task: Task)
}
