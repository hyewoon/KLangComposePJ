package com.hye.presentation.model

sealed class UIState<out T> {
    data object Loading: UIState<Nothing>()
    data class Success<T>(val data: T): UIState<T>()
    data class Error(val exception: Throwable): UIState<Nothing>()
}