package com.hye.domain.repository.mlkit

import com.hye.domain.model.mlkit.HandWritingStroke
import com.hye.domain.result.AppResult
import kotlinx.coroutines.flow.Flow

interface MLKitRepository {
    suspend fun recognize(strokes: List<HandWritingStroke>): Flow<AppResult<String>>
}