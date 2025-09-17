package com.example.to_do

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Color
import com.example.to_do.models.Task
import com.example.to_do.models.TaskType
import com.example.to_do.ui.theme.AddTaskDialog
import com.example.to_do.ui.theme.ToDoScreenWithInput


class MainActivity : ComponentActivity() {
    private val tasks = mutableStateListOf(
        Task(1, "Сделать проект", type = TaskType.WORK),
        Task(2, "Уборка дома", type = TaskType.HOME),
        Task(3, "Позвонить другу", type = TaskType.OTHER)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colorScheme = lightColorScheme(
                    primary = Color(0xFF6750A4),
                    secondary = Color(0xFF625B71),
                    surface = Color(0xFFF2F2F2)
                )
            ) {
                ToDoScreenWithInput(tasks)
            }
        }
    }
}
