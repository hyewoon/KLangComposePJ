package com.hye.data.repository

import com.hye.data.datasource.api.mapper.DetailResponseToDomainMapper
import com.hye.data.rest.RetrofitRESTService
import com.hye.domain.model.api.DetailWordEntity
import com.hye.domain.repository.api.DetailWordRepository
import com.hye.domain.result.AppResult
import javax.inject.Inject
import javax.inject.Named

class DetailWordRepositoryImpl @Inject constructor(
    @Named("apikey") private val apiKey: String,
    private val retrofitRESTService: RetrofitRESTService,
    private val mapper: DetailResponseToDomainMapper
): DetailWordRepository {
    override suspend fun getDetailWordInfo(targetCode: String): AppResult<DetailWordEntity> = runCatching{

        val response = retrofitRESTService.getDetailWordInfo(apiKey, targetCode)
    AppResult.Success(mapper.mapToDomain(response.items[0]))

    }.getOrElse {
        AppResult.Failure(it)

    }
}