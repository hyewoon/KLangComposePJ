package com.hye.domain.result

sealed class RoomResult<out T >{
    data object NoConstructor: RoomResult<Nothing>()
    data object Loading: RoomResult<Nothing>()
    data class Success<out T>(val data: T): RoomResult<T>()
    data class RoomDBError(val exception:Throwable ): RoomResult<Nothing>()

}
