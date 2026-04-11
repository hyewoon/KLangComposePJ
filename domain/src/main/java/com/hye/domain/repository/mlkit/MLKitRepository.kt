package com.hye.domain.repository.mlkit

import com.hye.domain.model.mlkit.HandWritingStroke
import com.hye.domain.result.AppResult


interface MLKitRepository {
    suspend fun initialize()
    suspend fun recognize(strokes: List<HandWritingStroke>): AppResult<String>
}