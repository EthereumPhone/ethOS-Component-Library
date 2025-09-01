package org.ethosmobile.components.library

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import org.ethosmobile.components.library.theme.SystemColorManager
import org.ethosmobile.components.library.util.DgenTextToolbarState


@Composable
fun DgenTextSelectionMenuContent(state: DgenTextToolbarState) {
    if (state.isShowing) {
        DisposableEffect(state.isShowing) {
            onDispose {
                // Cleanup if needed
            }
        }

        DgenSelectionMenu(
            rect = state.menuRect,
            onCopy = state.onCopy,
            onPaste = state.onPaste,
            onCut = state.onCut,
            onSelectAll = state.onSelectAll,
            onDismiss = { state.hide() },
            primaryColor = SystemColorManager.primaryColor
        )
    }
}