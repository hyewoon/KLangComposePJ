package com.hye.presentation.model

data class SearchWordUiState(
    val isLoading:Boolean=false,
    val word: String="",
    val english: String="",
    val pos: String="",
    val wordGrade: String ="",
    val definition: String = "",
    val example: String = "",
)
