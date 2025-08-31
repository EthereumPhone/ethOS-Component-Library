package org.ethosmobile.components.library.background

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import com.core.ui.util.oceanAbyss
import com.core.ui.util.pulseOpacity
import org.ethosmobile.components.library.R

@Composable
fun GifBackground(
    modifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier.alpha(pulseOpacity).offset(x = 250.dp, y = 64.dp).scale(1.6f).aspectRatio(1f),
    onClick: () -> Unit,
    gifEnabledLoader: ImageLoader,
    model: Any = R.drawable.globe_wireframe,
    primaryColor: Color = oceanAbyss,
    content: @Composable () -> Unit,
){
    Box(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                onClick()
            }
    ) {
        AsyncImage(
            modifier = imageModifier,
            imageLoader = gifEnabledLoader,
            model = model,
            contentDescription = null,
            colorFilter = ColorFilter.tint(primaryColor)
        )

        content()
    }
}