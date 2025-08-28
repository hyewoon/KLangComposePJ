package com.hye.domain.model.mlkit

data class HandWritingPoint(
    val x: Float,
    val y: Float,
    val timestamp: Long
)

data class HandWritingStroke(
    val pointData: List<HandWritingPoint>
)


data class HandWritingAnalysis(
    val level: ConfidenceLevel,
    val recognizedText: String = "",
    val candidates:List<String> = listOf(),
    val consistency: Float = 0f
)

enum class ConfidenceLevel {
    HIGH, MEDIUM, LOW
}