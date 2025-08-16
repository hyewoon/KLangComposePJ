package com.hye.domain.result

sealed class AppResult<out T >{
    data object NoConstructor: AppResult<Nothing>()
    data object Loading: AppResult<Nothing>()
    data class Success<out T>(val data: T): AppResult<T>()
    data class Failure(val exception:String ): AppResult<Nothing>()

}
