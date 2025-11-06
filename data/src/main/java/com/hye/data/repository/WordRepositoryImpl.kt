package com.hye.data.repository

import com.hye.data.datasource.api.mapper.BasicResponseToDomainMapper
import com.hye.data.rest.RetrofitRESTService
import com.hye.domain.model.api.WordEntity
import com.hye.domain.repository.api.WordRepository
import com.hye.domain.result.AppResult
import javax.inject.Inject
import javax.inject.Named

class WordRepositoryImpl @Inject constructor(
    @Named("apikey") private val apiKey: String,
    private val retrofitRESTService: RetrofitRESTService,
    private val mapper: BasicResponseToDomainMapper
): WordRepository {
    override suspend fun getWordInfo(inputWord: String): AppResult<List<WordEntity>> = runCatching {
        val response = retrofitRESTService.getWordInfo(apiKey, inputWord)

        response.item?.let { item ->
            AppResult.Success(item.map {
                mapper.mapToDomain(it)
            })
        } ?: AppResult.Success(emptyList())


        }.getOrElse {
            AppResult.Failure(it.message ?: "Unknown Error")

    }
}