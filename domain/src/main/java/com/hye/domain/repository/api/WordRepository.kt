package com.hye.domain.repository.api

import com.hye.domain.model.api.WordEntity
import com.hye.domain.result.AppResult

interface WordRepository {
    suspend fun getWordInfo(inputWord: String): AppResult<List<WordEntity>>
}