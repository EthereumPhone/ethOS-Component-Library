package com.example.componentlibrary.ui.components

import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.componentlibrary.ui.theme.blue
import com.example.componentlibrary.ui.theme.darkblue
import com.example.componentlibrary.ui.theme.error
import com.example.componentlibrary.ui.theme.ethOSTheme
import com.example.componentlibrary.ui.theme.gray
import com.example.componentlibrary.ui.theme.warning
import com.example.componentlibrary.ui.theme.white
import com.example.ethoscomponents.R

val Inter = FontFamily(
    Font(R.font.inter_light, FontWeight.Light),
    Font(R.font.inter_regular, FontWeight.Normal),
    Font(R.font.inter_medium, FontWeight.Medium),
    Font(R.font.inter_semibold, FontWeight.SemiBold),
    Font(R.font.inter_bold, FontWeight.Bold)
)
/*
@Composable
@Preview
fun ethOSDropDownMenuPreview() {
    ethOSTheme {
        val items = (1..5).map {
            "$it-ETH"
        }
        val s = rememberDropdownStateHolder(items)
        ethOSDropDownMenu(stateHolder = s)
    }
}
*/
@Composable
fun ethOSDropDownMenu(
    stateHolder: DropdownStateHolder,
    //initiallyOpened: Boolean = false,
) {
    var textfieldSize by remember { mutableStateOf(Size.Zero)}
    Column {
        OutlinedTextField(
            colors = TextFieldDefaults.textFieldColors(unfocusedIndicatorColor=blue,textColor = white, trailingIconColor = white, backgroundColor = blue),
            readOnly = true,
            value = stateHolder.value,
            textStyle = TextStyle( fontSize = 18.sp, fontWeight = FontWeight.SemiBold),
            onValueChange = { },//selectedText = it },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(blue)
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
                    Text(text = s, color = white, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}





