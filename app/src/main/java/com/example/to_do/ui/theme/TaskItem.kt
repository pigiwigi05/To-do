package com.example.to_do.ui.theme

import androidx.compose.ui.Alignment
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.to_do.models.Task
import com.example.to_do.models.TaskType
import kotlinx.coroutines.launch
import kotlin.math.abs

@Composable
fun TaskItemFinal(
    task: Task,
    onToggle: () -> Unit,
    onDelete: () -> Unit
) {
    val scope = rememberCoroutineScope()
    var offsetX by remember { mutableStateOf(0f) }

    val gradient = when (task.type) {
        TaskType.WORK -> Brush.horizontalGradient(listOf(Color(0xFF4facfe), Color(0xFF00f2fe)))
        TaskType.HOME -> Brush.horizontalGradient(listOf(Color(0xFF43e97b), Color(0xFF38f9d7)))
        TaskType.OTHER -> Brush.horizontalGradient(listOf(Color(0xFFfbc2eb), Color(0xFFa6c1ee)))
    }

    AnimatedVisibility(
        visible = true,
        enter = expandVertically(animationSpec = tween(300)) + fadeIn(animationSpec = tween(300)),
        exit = shrinkVertically(animationSpec = tween(300)) + fadeOut(animationSpec = tween(300))
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .pointerInput(Unit) {
                    detectHorizontalDragGestures { _, dragAmount ->
                        offsetX += dragAmount
                        if (abs(offsetX) > 200f) scope.launch { onDelete() }
                    }
                }
                .offset(x = offsetX.dp),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(gradient)
                    .padding(16.dp)
                    .clickable { onToggle() }
            ) {
                val icon = when (task.type) {
                    TaskType.WORK -> Icons.Default.Work
                    TaskType.HOME -> Icons.Default.Home
                    TaskType.OTHER -> Icons.Default.List
                }

                Icon(icon, contentDescription = null, tint = Color.White)
                Spacer(modifier = Modifier.width(12.dp))
                AnimatedContent(targetState = task.completed) { completed ->
                    Text(
                        text = task.title,
                        style = MaterialTheme.typography.bodyLarge.copy(color = Color.White),
                        textDecoration = if (completed) TextDecoration.LineThrough else null
                    )
                }
            }
        }
    }
}
