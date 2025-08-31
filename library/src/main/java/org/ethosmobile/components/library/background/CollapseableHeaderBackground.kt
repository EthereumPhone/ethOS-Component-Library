package org.ethosmobile.components.library.background

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import kotlin.math.abs
import kotlin.math.sign
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.core.ui.util.dgenBlack
import org.ethosmobile.components.library.background.ExtensionDensity.toDp
import org.ethosmobile.components.library.background.ExtensionDensity.toPx

object ExtensionDensity {

    inline val Dp.toPx @Composable get() = this.toPx(LocalDensity.current.density)
    fun Dp.toPx(density: Float) = value * density

    val Int.toDp @Composable get() = this.toDp(LocalDensity.current.density)
    fun Int.toDp(density: Float) = Dp((this / density))

    val Float.toDp @Composable get() = this.toDp(LocalDensity.current.density)
    fun Float.toDp(density: Float) = Dp((this / density))

}

object ColumnCollapsibleHeader2 {

    data class Properties(
        val min: Dp = 0.dp,
        val max: Dp = Dp.Infinity,
    )

    @Composable
    private fun rememberCollapsibleHeaderState(
        maxPxToConsume: Float,
        initialProgress: Float = 1f,
        initialScroll: Int = 0,
    ) = remember {
        CollapsibleHeaderState(maxPxToConsume, initialProgress, initialScroll)
    }

    private class CollapsibleHeaderState(
        private val maxPxToConsume: Float,
        initialProgress: Float,
        initialScroll: Int = 0,
    ) : NestedScrollConnection {

        val scrollState = ScrollState(initialScroll)
        private val _progress = mutableStateOf(initialProgress)

        var progress: Float
            get() = _progress.value
            set(value) {
                pxConsumed = maxPxToConsume * (1f - value)
            }

        private var pxConsumed: Float = maxPxToConsume * (1f - initialProgress)
            set(value) {
                field = value
                _progress.value = (1f - value / maxPxToConsume)
            }

        val progressPx get() = maxPxToConsume * progress

        private fun isDirectionPositive(value: Float) = value.sign < 0f

        override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
            return if (isDirectionPositive(available.y)) {
                onDirectionPositive(available)
            } else {
                onDirectionNegative(available)
            }
        }

        private fun onDirectionPositive(available: Offset): Offset {
            if (progress <= 0f) {
                return Offset.Zero
            }
            val allowedToBeConsumed = maxPxToConsume - pxConsumed
            val notConsumed = (abs(available.y) - allowedToBeConsumed)
            if (notConsumed <= 0f) {
                pxConsumed -= available.y
                return available
            }
            pxConsumed = maxPxToConsume.toFloat()
            return Offset(0f, -allowedToBeConsumed)
        }

        private fun onDirectionNegative(available: Offset): Offset {
            if (progress >= 1f) {
                return Offset.Zero
            }
            val availableToBeConsumed = available.y - scrollState.value
            if (availableToBeConsumed <= 0f) {
                return Offset.Zero
            }
            val allowedToBeConsumed = pxConsumed
            val notConsumed = availableToBeConsumed - allowedToBeConsumed
            if (notConsumed <= 0) {
                pxConsumed -= availableToBeConsumed
                return available
            }
            pxConsumed = 0f
            return Offset(0f, allowedToBeConsumed)

        }
    }

    /**
     * A composable that provides a collapsible header with a fading edge at the *bottom* of the header
     * (as opposed to the body). The fading edge becomes visible while the header is collapsing
     * (i.e. when [progress] < 1f).
     */
    @Composable
    operator fun invoke(
        modifier: Modifier = Modifier,
        properties: Properties,
        header: @Composable BoxScope.(progress: Float, progressDp: Dp, minHeight: Dp, maxHeight: Dp) -> Unit,
        body: @Composable ColumnScope.() -> Unit,
    ) {
        val density = LocalDensity.current.density
        val sizePx = remember {
            (properties.max - properties.min).toPx(density)
        }
        val collapsibleHeaderState =
            rememberCollapsibleHeaderState(sizePx)

        Column(
            modifier = modifier.drawWithCache {
                val gradientHeight = 56.dp.toPx()

                // Bottom fading edge (constant – screen bottom)
                val bottomEdgeBrush = Brush.verticalGradient(
                    colors = listOf(Color.Transparent, dgenBlack),
                    startY = size.height - gradientHeight,
                    endY = size.height
                )

                // Static value used inside onDrawWithContent to know where the header currently ends
                val minHeightPx = properties.min.toPx(density)

                onDrawWithContent {
                    drawContent()

                    // Always draw bottom edge so content fades out near navigation bar
                    drawRect(
                        brush = bottomEdgeBrush,
                        topLeft = Offset(0f, size.height - gradientHeight),
                        size = androidx.compose.ui.geometry.Size(size.width, gradientHeight)
                    )
                }
            }
        ) {

            Box(
                modifier = Modifier.fillMaxWidth().zIndex(3f)
            ) {
                header(
                    collapsibleHeaderState.progress,
                    properties.min + collapsibleHeaderState.progressPx.toDp(density),
                    properties.min,
                    properties.max
                )
            }
            Box(
                Modifier.fillMaxSize()
            ) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .nestedScroll(collapsibleHeaderState)
                        .verticalScroll(collapsibleHeaderState.scrollState)
                        .padding(bottom = 48.dp),
                    verticalArrangement = Arrangement.spacedBy(36.dp)
                ) {
                    body()
                }

                // Overlay fade below the header so body content scrolls into ocean → transparent gradient
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(24.dp)
                        .align(Alignment.TopCenter)
                        .background(
                            Brush.verticalGradient(
                                listOf(dgenBlack, Color.Transparent)
                            )
                        )
                )
            }
        }
    }
}