package com.hye.klangcomposepj.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Blue500,
    secondary = Blue300,
    tertiary = PointIvory
)

private val LightColorScheme = lightColorScheme(
    primary = Blue500,//주요 색상
    onPrimary = PointWhite, //파랑색 위의 텍스트
    secondary = Blue200,

    //Background
    background = Blue50, //전체 배경
    onBackground = Dark900,// 배경위의 텍스트

    tertiary = Dark200, //3차 색상
    onTertiary = Dark500, //3차 색상 위의 텍스트

    //surface(카드, topBar)
    surface = PointWhite, //surface배경(topBar, Card)
    onSurface = Dark900, //surface 위의 텍스트

    surfaceVariant = Dark50, //surface변형
    onSurfaceVariant = Dark900, //surface변형 위의 텍스트

    surfaceTint = Dark300,

)

@Composable
fun KLangComposePJTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}