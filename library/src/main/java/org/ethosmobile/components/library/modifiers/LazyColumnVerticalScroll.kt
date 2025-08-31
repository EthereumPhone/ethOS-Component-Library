package org.ethosmobile.components.library.modifiers

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.core.ui.util.dgenOcean
import com.core.ui.util.dgenTurqoise
import kotlin.math.max

@Composable
fun Modifier.verticalLazyListScrollbar(
    lazyListState: LazyListState,
    width: Dp = 6.dp,
    showScrollBarTrack: Boolean = true,
    scrollBarTrackColor: Color = dgenOcean,
    scrollBarColor: Color = dgenTurqoise,
    scrollBarCornerRadius: Float = 4f,
    trackTopInset: Dp = 32.dp,
    trackBottomInset: Dp = 32.dp,
    endPadding: Dp = 12.dp,
    minThumbHeight: Dp = 40.dp,
    verticalOffset: Dp = 0.dp
): Modifier {
    val layoutInfo = lazyListState.layoutInfo
    val totalItemsCount = layoutInfo.totalItemsCount
    val firstVisibleItemIndex = lazyListState.firstVisibleItemIndex
    val firstVisibleItemScrollOffset = lazyListState.firstVisibleItemScrollOffset
    val visibleItemsInfo = layoutInfo.visibleItemsInfo
    val viewportHeightPx = (layoutInfo.viewportEndOffset - layoutInfo.viewportStartOffset)
        .toFloat()
        .coerceAtLeast(1f)

    // Smooth, stable per-item height estimate to avoid jumping thumb size/position
    var smoothedItemHeightPx by remember(totalItemsCount) { mutableStateOf<Float?>(null) }
    val currentVisibleAverage = if (visibleItemsInfo.isNotEmpty()) {
        visibleItemsInfo.sumOf { it.size }.toFloat() / visibleItemsInfo.size
    } else null
    if (currentVisibleAverage != null) {
        smoothedItemHeightPx = when (val prev = smoothedItemHeightPx) {
            null -> currentVisibleAverage
            else -> prev + (currentVisibleAverage - prev) * 0.15f
        }
    }
    val itemHeightEstimatePx = smoothedItemHeightPx ?: viewportHeightPx

    val contentHeightEstimatePx = (itemHeightEstimatePx * totalItemsCount)
        .coerceAtLeast(viewportHeightPx)
    val scrolledOffsetPx = (firstVisibleItemIndex * itemHeightEstimatePx) + firstVisibleItemScrollOffset
    val maxScrollPx = (contentHeightEstimatePx - viewportHeightPx).coerceAtLeast(1f)
    var progressFraction = (scrolledOffsetPx / maxScrollPx).coerceIn(0f, 1f)

    // Force exact ends when at the start or when the last item is fully visible
    if (firstVisibleItemIndex == 0 && firstVisibleItemScrollOffset <= 0) {
        progressFraction = 0f
    } else if (visibleItemsInfo.isNotEmpty()) {
        val lastVisible = visibleItemsInfo.last()
        val lastVisibleBottom = (lastVisible.offset + lastVisible.size).toFloat()
        if (lastVisible.index >= totalItemsCount - 1 && lastVisibleBottom <= viewportHeightPx + 0.5f) {
            progressFraction = 1f
        }
    }

    return this.then(
        Modifier.drawWithContent {
            drawContent()

            if (totalItemsCount == 0) return@drawWithContent

            // Track geometry (right side with end padding)
            val widthPx = width.toPx()
            val endPaddingPx = endPadding.toPx()
            val xRight = size.width - endPaddingPx - widthPx
            val yShiftPx = verticalOffset.toPx()
            val trackStartY = trackTopInset.toPx() + yShiftPx
            val trackEndY = (size.height - trackBottomInset.toPx() + yShiftPx)
                .coerceAtLeast(trackStartY)
            val trackHeight = (trackEndY - trackStartY).coerceAtLeast(0f)

            // Pill shape: ensure fully rounded ends regardless of provided corner radius
            val effectiveCorner = max(scrollBarCornerRadius, widthPx / 2f)
            val cornerRadius = CornerRadius(effectiveCorner)

            // Thumb size: proportional to viewport vs content; clamped to min height
            val thumbFractionOfTrack = (viewportHeightPx / contentHeightEstimatePx)
                .coerceIn(0.06f, 1f)
            val thumbHeightPx = (trackHeight * thumbFractionOfTrack)
                .coerceAtLeast(minThumbHeight.toPx())
                .coerceAtMost(trackHeight)

            // Thumb position mapped smoothly to the full track range
            val maxThumbOffset = (trackHeight - thumbHeightPx).coerceAtLeast(0f)
            val thumbOffsetYPx = (progressFraction * maxThumbOffset)
                .coerceIn(0f, maxThumbOffset)

            if (showScrollBarTrack) {
                drawRoundRect(
                    color = scrollBarTrackColor,
                    cornerRadius = cornerRadius,
                    topLeft = Offset(xRight, trackStartY),
                    size = Size(widthPx, trackHeight)
                )
            }

            drawRoundRect(
                color = scrollBarColor,
                cornerRadius = cornerRadius,
                topLeft = Offset(xRight, trackStartY + thumbOffsetYPx),
                size = Size(widthPx, thumbHeightPx)
            )
        }
    )
}