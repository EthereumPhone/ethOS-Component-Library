package com.example.componentlibrary.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.shapes
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

private val ethOSScheme = darkColors(
    primary = darkblue,
    secondary = blue,
    background = darkblue,
    surface = blue,
    onSurface = white,
    onPrimary = white,
    onSecondary = white,
    onBackground = white,

)



@Composable
fun ethOSTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = ethOSScheme
    CompositionLocalProvider(LocalSpacing provides Spacing()){
        MaterialTheme(
            colors = colorScheme,
            content = content,
            shapes = shapes,
            typography = ethOSTypography
        )
    }

}