package org.ethosmobile.components.library.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.shapes
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider


private val ethOSScheme = darkColors(
    primary = Color.DARK_BLUE,
    secondary = Color.BLUE,
    background = Color.DARK_BLUE,
    surface = Color.BLUE,
    onSurface = Color.WHITE,
    onPrimary = Color.WHITE,
    onSecondary = Color.WHITE,
    onBackground = Color.WHITE
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