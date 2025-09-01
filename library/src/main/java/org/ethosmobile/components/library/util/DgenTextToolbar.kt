package org.ethosmobile.components.library.util

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.platform.TextToolbar
import androidx.compose.ui.platform.TextToolbarStatus

/**
 * Custom Text Toolbar implementation
 */
class DgenTextToolbar(
    private val state: DgenTextToolbarState
) : TextToolbar
{

    override val status: TextToolbarStatus
        get() = if (state.isShowing) TextToolbarStatus.Shown else TextToolbarStatus.Hidden

    override fun hide() {
        state.hide()
    }

    override fun showMenu(
        rect: Rect,
        onCopyRequested: (() -> Unit)?,
        onPasteRequested: (() -> Unit)?,
        onCutRequested: (() -> Unit)?,
        onSelectAllRequested: (() -> Unit)?
    ) {
        if (!state.isShowing) {
            state.show(rect, onCopyRequested, onPasteRequested, onCutRequested, onSelectAllRequested)
        }
    }
}