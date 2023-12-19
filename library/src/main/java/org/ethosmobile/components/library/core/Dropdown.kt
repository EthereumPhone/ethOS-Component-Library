package org.ethosmobile.components.library.core

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import org.ethosmobile.components.library.utils.DropdownStateHolder
import org.ethosmobile.components.library.utils.rememberDropdownStateHolder
import org.ethosmobile.components.library.theme.ethOSTheme
import org.ethosmobile.components.library.theme.Colors
import org.ethosmobile.components.library.theme.Fonts


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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ethOSDropDownMenu(
    stateHolder: DropdownStateHolder,
    //initiallyOpened: Boolean = false,
) {
    var textfieldSize by remember { mutableStateOf(Size.Zero)}
    Column() {
        OutlinedTextField(
            colors = TextFieldDefaults.colors(
                focusedTextColor = Colors.WHITE,
                focusedContainerColor = Colors.DARK_GRAY,
                unfocusedContainerColor = Colors.DARK_GRAY,
                disabledContainerColor = Colors.DARK_GRAY,
                unfocusedIndicatorColor = Colors.DARK_GRAY,
                unfocusedTrailingIconColor = Colors.WHITE,
            ),
            readOnly = true,
            value = stateHolder.value,
            textStyle = TextStyle( fontSize = 18.sp, fontWeight = FontWeight.SemiBold),
            onValueChange = { },//selectedText = it },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(6.dp))
                .background(Colors.DARK_GRAY)
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textfieldSize = coordinates.size.toSize()
                }
                .clickable { stateHolder.onEnabled(true) },
            trailingIcon = {
                Icon(stateHolder.icon,"contentDescription",
                    Modifier.clickable { stateHolder.onEnabled(true) }
                )
            }
        )
        DropdownMenu(
            expanded = stateHolder.enabled,
            onDismissRequest = { stateHolder.onEnabled(false)},
            modifier = Modifier.width(with(LocalDensity.current){textfieldSize.width.toDp()})
        ) {
            stateHolder.items.forEachIndexed { index, s ->
                DropdownMenuItem(
                    text= {Text(
                        text = s,
                        color = Colors.WHITE,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = Fonts.INTER,
                    )},
                    onClick = {
                    stateHolder.onSelectedIndex(index)
                    stateHolder.onEnabled(false)
                })
            }
        }
    }
}





