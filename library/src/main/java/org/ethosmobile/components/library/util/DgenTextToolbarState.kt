package org.ethosmobile.components.library.util

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import com.core.ui.util.dgenTurqoise

/**
 * State holder for custom text toolbar
 */
@Stable
class DgenTextToolbarState(
    private val primaryColor: Color = dgenTurqoise
)
{
    var isShowing by mutableStateOf(false)
        private set
    var menuRect by mutableStateOf(Rect.Zero)
        private set
    var onCopy: (() -> Unit)? by mutableStateOf(null)
        private set
    var onPaste: (() -> Unit)? by mutableStateOf(null)
        private set
    var onCut: (() -> Unit)? by mutableStateOf(null)
        private set
    var onSelectAll: (() -> Unit)? by mutableStateOf(null)
        private set

    fun show(
        rect: Rect,
        onCopyRequested: (() -> Unit)?,
        onPasteRequested: (() -> Unit)?,
        onCutRequested: (() -> Unit)?,
        onSelectAllRequested: (() -> Unit)?
    ) {
        menuRect = rect
        onCopy = onCopyRequested
        onPaste = onPasteRequested
        onCut = onCutRequested
        onSelectAll = onSelectAllRequested
        isShowing = true
    }

    fun hide() {
        isShowing = false
    }
}