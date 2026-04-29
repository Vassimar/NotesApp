package com.example.noteswithregistration.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TaskEntity::class], version = 1)
internal abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}