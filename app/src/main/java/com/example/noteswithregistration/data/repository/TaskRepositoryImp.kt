package com.example.noteswithregistration.data.repository

import com.example.noteswithregistration.data.db.TaskDao
import com.example.noteswithregistration.data.mapper.toDomain
import com.example.noteswithregistration.data.mapper.toEntity
import com.example.noteswithregistration.domain.model.Task
import com.example.noteswithregistration.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class TaskRepositoryImp(private val taskDao: TaskDao) : TaskRepository {
    override fun observeActiveTasks(): Flow<List<Task>> {
        return taskDao.observeActiveTasks().map { taskEntities ->
            taskEntities.map { it.toDomain() }

        }
    }

    override fun observeAllTasks(): Flow<List<Task>> {
        return taskDao.observeAllTasks().map { taskEntities ->
            taskEntities.map { it.toDomain() }
        }
    }

    override fun observeTaskById(id: Int): Flow<Task?> {
        return taskDao.observeTaskById(id).map { it?.toDomain() }
    }

    override suspend fun addTask(task: Task) {
        taskDao.insert(task.toEntity())
    }

    override suspend fun updateTask(task: Task) {
        taskDao.update(task.toEntity())
    }

    override suspend fun deleteTask(task: Task) {
        taskDao.delete(task.toEntity())
    }

}