package com.hye.presentation.util

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import java.util.Locale

fun changeAppLanguage(context: Context, languageCode: String) {
    val locale = Locale(languageCode)
    Locale.setDefault(locale)

    val config = Configuration(context.resources.configuration)
    config.setLocale(locale)

    context.createConfigurationContext(config)
    (context as? Activity)?.recreate()
}