package com.hye.presentation.model

import androidx.annotation.StringRes
import com.hye.domain.model.AppLanguage
import com.hye.presentation.R


@get:StringRes
val AppLanguage.nameResId:Int
    get() = when(this){
        AppLanguage.ENGLISH -> R.string.language_english
         else -> R.string.language_korean
    }
