package com.example.noteswithregistration.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM TaskEntity")
    fun getAll(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM TaskEntity WHERE isActive = 1")
    fun getActive(): Flow<List<TaskEntity>>
    @Query(value = "SELECT * FROM TaskEntity WHERE id = :taskId")
    fun getTaskById(taskId: Int): Flow<TaskEntity>

    @Insert
    suspend fun insert(taskEntity: TaskEntity)

    @Update
    suspend fun update(taskEntity: TaskEntity)

    @Delete
    suspend fun delete(taskEntity: TaskEntity)

}