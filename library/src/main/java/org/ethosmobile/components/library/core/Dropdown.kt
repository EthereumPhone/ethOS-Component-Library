package org.ethosmobile.components.library.core

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import org.ethosmobile.components.library.theme.ethOSTheme
import org.ethosmobile.components.library.theme.Color
import org.ethosmobile.components.library.theme.Font


@Composable
//@Preview
fun ethOSDropDownMenuPreview() {
    ethOSTheme {
        val items = (1..5).map {
            "$it-ETH"
        }
        val s = rememberDropdownStateHolder(items)
        ethOSDropDownMenu(stateHolder = s)
    }
}

@Composable
fun ethOSDropDownMenu(
    stateHolder: DropdownStateHolder,
    //initiallyOpened: Boolean = false,
) {
    var textfieldSize by remember { mutableStateOf(Size.Zero)}


    Column {
        Surface(
            color = Color.BLUE,
            shape = RoundedCornerShape(8.dp),
            contentColor = Color.WHITE,
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textfieldSize = coordinates.size.toSize()
                }
            ,

            ){
            Box (
                Modifier
                    .clickable { stateHolder.onEnabled(true) }
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ){
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxSize()
                ){
                    Text(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        text = stateHolder.value)
                    Icon(stateHolder.icon,"contentDescription",
                        Modifier.clickable { stateHolder.onEnabled(true) }
                    )
                }
            }

        }

        DropdownMenu(
                expanded = stateHolder.enabled,
        onDismissRequest = { stateHolder.onEnabled(false)},
        modifier = Modifier.width(with(LocalDensity.current){textfieldSize.width.toDp()})
        ) {
        stateHolder.items.forEachIndexed { index, s ->
            var selectedColor = if (s == stateHolder.value) Color.BLUE else Color.LIGHT_BLUE
            DropdownMenuItem(
                modifier = Modifier.background(selectedColor),
                onClick = {
                    stateHolder.onSelectedIndex(index)
                    stateHolder.onEnabled(false)
                }) {
                Text(text = s, color = Color.WHITE, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            }
        }
    }
    }





}







