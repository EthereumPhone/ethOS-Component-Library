package org.ethosmobile.components.library

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import org.ethosmobile.components.library.theme.SystemColorManager

@Composable
fun DgenSelectionMenu(
    rect: Rect,
    onCopy: (() -> Unit)?,
    onPaste: (() -> Unit)?,
    onCut: (() -> Unit)?,
    onSelectAll: (() -> Unit)?,
    onDismiss: () -> Unit,
    primaryColor: Color = SystemColorManager.primaryColor
) {
    // Get screen dimensions
    val configuration = androidx.compose.ui.platform.LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val density = androidx.compose.ui.platform.LocalDensity.current

    // Calculate actual menu dimensions based on buttons
    val buttonCount = listOfNotNull(onCut, onCopy, onPaste, onSelectAll).size
    val menuWidth = (buttonCount * 50 + 20).dp // Approximate width based on button count
    val menuHeight = 44.dp // Reduced height for better fit
    val menuWidthPx = with(density) { menuWidth.toPx() }
    val menuHeightPx = with(density) { menuHeight.toPx() }
    val screenWidthPx = with(density) { screenWidth.toPx() }
    val screenHeightPx = with(density) { screenHeight.toPx() }

    // Much larger spacing to ensure text is always visible
    val preferredSpacing = with(density) { 80.dp.toPx() }  // Extra large spacing - text will definitely be visible
    val minimalSpacing = with(density) { 60.dp.toPx() }    // Even in tight spaces, keep good distance
    val edgePadding = with(density) { 20.dp.toPx() }       // Increased edge padding too

    // Calculate horizontal position
    var xOffset = if (rect.width > 0) {
        // Center the menu over the selection
        (rect.left + rect.width / 2 - menuWidthPx / 2).toInt()
    } else {
        // Position at cursor for paste on empty field
        (rect.left - menuWidthPx / 2).toInt()
    }

    // Ensure menu stays within screen bounds horizontally with smart adjustment
    if (xOffset < edgePadding) {
        // Too close to left edge
        xOffset = edgePadding.toInt()
    } else if (xOffset + menuWidthPx > screenWidthPx - edgePadding) {
        // Too close to right edge
        xOffset = (screenWidthPx - menuWidthPx - edgePadding).toInt()
    }

    // Calculate vertical position with preference for above to avoid covering text
    val spaceAbove = rect.top
    val spaceBelow = screenHeightPx - rect.bottom
    val textHeight = rect.height

    val yOffset: Int

    // Prefer placing above the selection to avoid covering text
    if (spaceAbove >= menuHeightPx + preferredSpacing) {
        // Place above with good spacing
        yOffset = (rect.top - menuHeightPx - preferredSpacing).toInt()
    } else if (spaceBelow >= menuHeightPx + preferredSpacing && textHeight < with(density) { 100.dp.toPx() }) {
        // Place below only if text is not too tall (to avoid covering multi-line selections)
        yOffset = (rect.bottom + preferredSpacing).toInt()
    } else if (spaceAbove > menuHeightPx + minimalSpacing) {
        // Place above with minimal spacing if needed
        yOffset = (rect.top - menuHeightPx - minimalSpacing).toInt()
    } else if (spaceBelow > menuHeightPx + minimalSpacing) {
        // Place below with minimal spacing
        yOffset = (rect.bottom + minimalSpacing).toInt()
    } else {
        // Last resort: place at top or bottom of screen with extra spacing from text
        val rectCenterY = rect.top + rect.height / 2
        if (rectCenterY < screenHeightPx / 2) {
            // Selection is in top half, place menu at bottom
            yOffset = (screenHeightPx - menuHeightPx - edgePadding).toInt()
        } else {
            // Selection is in bottom half, place menu at top
            yOffset = edgePadding.toInt()
        }
    }

    val secondaryColor = SystemColorManager.secondaryColor

    Popup(
        alignment = Alignment.TopStart,
        offset = androidx.compose.ui.unit.IntOffset(xOffset, yOffset),
        onDismissRequest = onDismiss,
        properties = PopupProperties(
            focusable = false,
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        androidx.compose.material3.Card(
            modifier = Modifier
                .shadow(8.dp, RoundedCornerShape(12.dp))
                .background(secondaryColor, RoundedCornerShape(12.dp)),
            colors = CardDefaults.cardColors(
                containerColor = secondaryColor
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Row(
                modifier = Modifier.padding(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                onCut?.let {
                    DgenTextSelectionMenuButton(
                        text = "CUT",
                        onClick = {
                            it()
                            onDismiss()
                        },
                        primaryColor = primaryColor
                    )
                }

                onCopy?.let {
                    DgenTextSelectionMenuButton(
                        text = "COPY",
                        onClick = {
                            it()
                            onDismiss()
                        },
                        primaryColor = primaryColor
                    )
                }

                onPaste?.let {
                    DgenTextSelectionMenuButton(
                        text = "PASTE",
                        onClick = {
                            it()
                            onDismiss()
                        },
                        primaryColor = primaryColor
                    )
                }

                onSelectAll?.let {
                    DgenTextSelectionMenuButton(
                        text = "ALL",
                        onClick = {
                            it()
                            onDismiss()
                        },
                        primaryColor = primaryColor
                    )
                }
            }
        }
    }
}