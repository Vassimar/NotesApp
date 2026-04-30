package com.example.noteswithregistration.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
internal data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val isActive: Boolean = false,
)
