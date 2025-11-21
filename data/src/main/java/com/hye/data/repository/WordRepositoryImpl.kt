package com.hye.data.repository

import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import com.hye.data.datasource.api.mapper.BasicResponseToDomainMapper
import com.hye.data.rest.RetrofitRESTService
import com.hye.domain.model.api.WordEntity
import com.hye.domain.repository.api.WordRepository
import com.hye.domain.result.AppResult
import com.tickaroo.tikxml.XmlDataException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Named

class WordRepositoryImpl @Inject constructor(
    @Named("apikey")
    private val apiKey: String,
    private val retrofitRESTService: RetrofitRESTService,
    private val mapper: BasicResponseToDomainMapper
): WordRepository {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun getWordInfo(inputWord: String): AppResult<List<WordEntity>> {
        return try {
            val response = retrofitRESTService.getWordInfo(apiKey, inputWord)
            AppResult.Success(response.item?.map { mapper.mapToDomain(it) } ?:emptyList())
        }catch(e: IOException) {
            Log.e("Repository", "네트워크 에러", e)
            AppResult.Failure(e)
        }catch(e: HttpException) {
            Log.e("Repository", "서버 에러", e)
            AppResult.Failure(e)
        }catch(e: XmlDataException){
            Log.e("Repository","파싱에러",e)
            Log.e("Repository,","실패한 필드 : ${e.message}", e)
            e.printStackTrace()
            AppResult.Failure(e)
        }catch(e:Exception){
            Log.e("WordRepositoryImpl", "에러발생",e )
            e.printStackTrace()
            AppResult.Failure(e)

        }




    }
}