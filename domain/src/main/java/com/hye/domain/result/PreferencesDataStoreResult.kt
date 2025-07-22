package com.hye.domain.result

sealed class PreferencesDataStoreResult<out T> {
    data object NoConstructor : PreferencesDataStoreResult<Nothing>()
    data object Loading : PreferencesDataStoreResult<Nothing>()
    data class Success<T>(val data: T) : PreferencesDataStoreResult<T>()
    data class Failure(val exception: Throwable) : PreferencesDataStoreResult<Nothing>()
}
