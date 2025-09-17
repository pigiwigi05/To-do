@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.to_do.ui.theme

import androidx.compose.material3.Text
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.to_do.models.Task
import androidx.compose.runtime.*


@Composable
fun ToDoScreenWithInput(tasks: MutableList<Task>) {
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Мои задачи") }) },
        floatingActionButton = { AnimatedFAB { showDialog = true } }
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(tasks) { index, task ->
                TaskItemFinal(
                    task = task,
                    onToggle = { tasks[index] = task.copy(completed = !task.completed) },
                    onDelete = { tasks.removeAt(index) }
                )
            }
        }

        if (showDialog) {
            AddTaskDialog(
                onAdd = { text ->
                    tasks.add(Task(tasks.size + 1, text))
                },
                onDismiss = { showDialog = false }
            )
        }
    }
}