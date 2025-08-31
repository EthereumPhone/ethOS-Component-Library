package org.ethosmobile.components.library.background

import kotlin.math.roundToInt
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.compose.ui.platform.LocalLifecycleOwner
import com.core.ui.util.dgenBlack
import org.ethosmobile.components.library.opengl.view.OpenGLGlobeView


@Composable
fun OpenGLBackground(
    modifier: Modifier = Modifier,
    globeRenderer: OpenGLGlobeView,
    globeColor: androidx.compose.ui.graphics.Color,
    lineWidth: Float = 2f,
    content: @Composable () -> Unit,
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    // Manage GLSurfaceView lifecycle and throttle rendering when not animating
    DisposableEffect(lifecycleOwner, globeRenderer) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START, Lifecycle.Event.ON_RESUME -> {
                    globeRenderer.setAnimating(true)
                    globeRenderer.onResumeView()
                }
                Lifecycle.Event.ON_PAUSE, Lifecycle.Event.ON_STOP -> {
                    globeRenderer.setAnimating(false)
                    globeRenderer.onPauseView()
                }
                else -> {}
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            globeRenderer.setAnimating(false)
            globeRenderer.onPauseView()
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // OpenGL rendering in the background

            AndroidView(
                factory = { globeRenderer.apply {
                    // renderMode controlled by setAnimating
                }
                },
                modifier = modifier
                    .drawWithContent {
                        drawContent()
                        drawRect(color = dgenBlack, alpha = 0.6f)
                    }
                    .align(Alignment.Center).size(300.dp)//.fillMaxSize() // Match the size of the parent container
            )




        content()

    }

    // Update OpenGL renderer color when globeColor changes
    LaunchedEffect(globeColor, lineWidth) {
        val r = (globeColor.red * 255).roundToInt()
        val g = (globeColor.green * 255).roundToInt()
        val b = (globeColor.blue * 255).roundToInt()
        val hexColor = String.format("#%02X%02X%02X", r, g, b)
        globeRenderer.setColor(hexColor)
        globeRenderer.renderer.linewidth = lineWidth
    }
}



