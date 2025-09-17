package com.example.to_do.ui.theme

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
fun AnimatedFAB(onClick: () -> Unit) {
    AnimatedVisibility(
        visible = true,
        enter = fadeIn(animationSpec = tween(400)) + scaleIn(),
        exit = fadeOut(animationSpec = tween(400)) + scaleOut()
    ) {
        FloatingActionButton(onClick = onClick) {
            Icon(Icons.Default.Add, contentDescription = "Добавить задачу")
        }
    }
}
