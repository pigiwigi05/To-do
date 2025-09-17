package com.example.to_do.models

enum class TaskType { WORK, HOME, OTHER }

data class Task(
    val id: Int,
    val title: String,
    var completed: Boolean = false,
    val type: TaskType = TaskType.OTHER
)

