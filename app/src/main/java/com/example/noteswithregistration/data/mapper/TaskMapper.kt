package com.example.noteswithregistration.data.mapper

import com.example.noteswithregistration.data.db.TaskEntity
import com.example.noteswithregistration.domain.model.Task

internal fun TaskEntity.toDomain(): Task = Task(
    id = id,
    title = title,
    description = description,
    isActive = isActive
)

internal fun Task.toEntity(): TaskEntity = TaskEntity(
    id = id,
    title = title,
    description = description,
    isActive = isActive
)
