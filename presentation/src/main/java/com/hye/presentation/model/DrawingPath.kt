package com.hye.presentation.model

import androidx.compose.ui.graphics.Path


data class DrawingPath(
    val path: Path,
    val color: androidx.compose.ui.graphics.Color,
    val strokeWidth: Float
)
