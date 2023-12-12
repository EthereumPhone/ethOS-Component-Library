package org.ethosmobile.components.library.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.shapes
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider


private val ethOSScheme = darkColors(
    primary = Colors.DARK_BLUE,
    secondary = Colors.BLUE,
    background = Colors.DARK_BLUE,
    surface = Colors.BLUE,
    onSurface = Colors.WHITE,
    onPrimary = Colors.WHITE,
    onSecondary = Colors.WHITE,
    onBackground = Colors.WHITE
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