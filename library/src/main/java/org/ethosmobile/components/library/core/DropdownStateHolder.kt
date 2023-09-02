package org.ethosmobile.components.library.core

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.vector.ImageVector

class DropdownStateHolder(data:List<String>) {
    var enabled by mutableStateOf(false)

    var seledtedIndex by mutableStateOf(-1)
    var size by mutableStateOf(Size.Zero)
    val icon: ImageVector
        @Composable get() = if (enabled){
            Icons.Default.KeyboardArrowUp
        }else{
            Icons.Default.KeyboardArrowDown
        }
    /*val items = (1..5).map {
        "option $it"
    }*/

    val items = data
    var value by mutableStateOf(items[0])
    fun onEnabled(newVal:Boolean){
        enabled = newVal
    }
    fun onSelectedIndex(newVal:Int){
        seledtedIndex = newVal
        value = items[seledtedIndex]
    }
    fun onSize(newVal:Size){
        size = newVal
    }
}

@Composable
fun rememberDropdownStateHolder(data:List<String>) = remember(data) {
    DropdownStateHolder(data)
}