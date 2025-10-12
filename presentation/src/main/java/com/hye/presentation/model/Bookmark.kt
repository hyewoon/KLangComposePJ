package com.hye.presentation.model

import androidx.compose.ui.graphics.vector.ImageVector
import com.hye.presentation.R

data class Bookmark(
    val korean: String = "",
    val english: String ="",
    val star: Int = R.drawable.star
)
