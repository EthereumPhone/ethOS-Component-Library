package org.ethosmobile.components.library.core

import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.ethosmobile.components.library.R
import org.ethosmobile.components.library.theme.Colors


enum class SnackbarState {
    DEFAULT,
    ERROR,
    SUCCESS,
    WARNING
}

class SnackbarDelegate(
    hostState: SnackbarHostState,
    coroutine: CoroutineScope
) {
    var snackbarHostState by mutableStateOf(hostState)
    var coroutineScope by mutableStateOf(coroutine)

    var snackbarState: SnackbarState = SnackbarState.DEFAULT


    var snackbarBackgroundColor = Colors.LIGHT_BLUE

    var snackbarOnColor = Colors.WHITE

    var snackbarIcon = R.drawable.outline_info_24



    fun showSnackbar(
        state: SnackbarState,
        message: String,
        actionLabel: String? = null,
        duration: SnackbarDuration = SnackbarDuration.Short
    ){
        this.snackbarState = state

        this.snackbarBackgroundColor = when (this.snackbarState) {
            SnackbarState.DEFAULT -> Colors.LIGHT_BLUE
            SnackbarState.ERROR -> Colors.ERROR
            SnackbarState.SUCCESS -> Colors.SUCCESS
            SnackbarState.WARNING -> Colors.WARNING
        }
        this.snackbarOnColor = if( snackbarState == SnackbarState.WARNING) Colors.GRAY else Colors.WHITE

        this.snackbarIcon = when (this.snackbarState) {
            SnackbarState.DEFAULT -> R.drawable.outline_info_24
            SnackbarState.ERROR -> R.drawable.outline_error_outline_24
            SnackbarState.SUCCESS -> R.drawable.baseline_check_24
            SnackbarState.WARNING -> R.drawable.outline_warning_24
        }

        coroutineScope?.launch {
            snackbarHostState?.showSnackbar(message, actionLabel, duration)
        }
    }

}

@Composable
fun rememberSnackbarDelegate(
    hostState: SnackbarHostState,
    coroutine: CoroutineScope
) = remember(hostState,coroutine) {
    SnackbarDelegate(hostState,coroutine)
}