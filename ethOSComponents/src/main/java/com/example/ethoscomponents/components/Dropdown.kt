package com.example.ethoscomponents.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
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
import com.example.ethoscomponents.theme.ethOSTheme
import com.example.ethoscomponents.theme.Color


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
    Column() {
        OutlinedTextField(
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = Color.BLUE,
                textColor = Color.WHITE,
                trailingIconColor = Color.WHITE,
                backgroundColor = Color.BLUE
            ),
            readOnly = true,
            value = stateHolder.value,
            textStyle = TextStyle( fontSize = 18.sp, fontWeight = FontWeight.SemiBold),
            onValueChange = { },//selectedText = it },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(6.dp))
                .background(Color.BLUE)
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
                DropdownMenuItem(onClick = {
                    stateHolder.onSelectedIndex(index)
                    stateHolder.onEnabled(false)
                }) {
                    Text(text = s, color = Color.WHITE, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}





