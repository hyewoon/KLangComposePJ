package com.hye.data.repository

import com.hye.domain.model.mlkit.HandWritingStroke
import com.hye.domain.repository.mlkit.MLKitRepository
import com.hye.domain.result.AppResult
import kotlinx.coroutines.flow.Flow


class MLKitRepositoryImpl : MLKitRepository {

    override suspend fun recognize(strokes: List<HandWritingStroke>): Flow<AppResult<String>> {
        TODO("Not yet implemented")
    }
}

