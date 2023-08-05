package com.example.componentlibrary.ui.components

import androidx.compose.material.SnackbarDefaults
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.example.componentlibrary.ui.theme.blue
import com.example.componentlibrary.ui.theme.darkbrown
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.example.componentlibrary.ui.theme.error
import com.example.componentlibrary.ui.theme.lightblue
import com.example.componentlibrary.ui.theme.warning
import com.example.componentlibrary.ui.theme.positive
import com.example.componentlibrary.ui.theme.white
import com.example.ethoscomponents.R

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


    var snackbarBackgroundColor = lightblue

    var snackbarOnColor = white

    var snackbarIcon = R.drawable.outline_info_24



    fun showSnackbar(
        state: SnackbarState,
        message: String,
        actionLabel: String? = null,
        duration: SnackbarDuration = SnackbarDuration.Short
    ){
        this.snackbarState = state

        this.snackbarBackgroundColor = when (this.snackbarState) {
            SnackbarState.DEFAULT -> lightblue
            SnackbarState.ERROR -> error
            SnackbarState.SUCCESS -> positive
            SnackbarState.WARNING -> warning
        }
        this.snackbarOnColor = if( snackbarState == SnackbarState.WARNING ) darkbrown else white

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