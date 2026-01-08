package com.hye.domain.repository.api

import com.hye.domain.model.api.DetailWordEntity
import com.hye.domain.result.AppResult

interface DetailWordRepository {
    suspend fun getDetailWordInfo(targetCode: String ): AppResult<DetailWordEntity>
}