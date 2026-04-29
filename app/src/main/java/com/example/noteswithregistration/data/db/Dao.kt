package com.example.noteswithregistration.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
internal interface TaskDao {
    @Query("SELECT * FROM TaskEntity")
    fun observeAllTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM TaskEntity WHERE isActive = 1")
    fun observeActiveTasks(): Flow<List<TaskEntity>>
    @Query(value = "SELECT * FROM TaskEntity WHERE id = :taskId")
    fun observeTaskById(taskId: Int?): Flow<TaskEntity?>

    @Insert
    suspend fun insert(taskEntity: TaskEntity)

    @Update
    suspend fun update(taskEntity: TaskEntity)

    @Delete
    suspend fun delete(taskEntity: TaskEntity)

}