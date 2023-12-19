package org.ethosmobile.components.library.lightnode

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text


import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import org.ethosmobile.components.library.theme.Colors
import org.ethosmobile.components.library.theme.Fonts

@Composable
fun ethOSSwitchBlock(
    modifier: Modifier= Modifier,
    options: Array<String>,
    onSelectedOption: (String) -> Unit,
    title: String = "",
    hasTitle: Boolean,
    icon: @Composable () -> Unit,
) {
    var expanded =  remember { mutableStateOf(false) }
    var selectedOption =  remember { mutableStateOf(options[0]) }

    Column (
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
    ) {
        if (hasTitle) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    //modifier = modifier.padding(start = 16.dp),
                    text = title,
                    fontSize = 16.sp,
                    color = Colors.GRAY
                )
                Spacer(modifier = Modifier.width(8.dp))
                icon()
            }
        }
        Column(
            modifier = modifier
                .clip(RoundedCornerShape(12.dp))
                .background(Colors.BLACK)
        ) {

            Box(
                modifier = modifier
                    .clickable {
                        expanded.value = !expanded.value
                    }

            ) {
                Text(
                    text = selectedOption.value,
                    color = Colors.WHITE,
                    lineHeight = 109.sp,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = Fonts.INTER
                    ),
                    modifier = Modifier.padding(vertical = 16.dp)
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    tint = Colors.WHITE,
                    modifier = Modifier
                        .align(Alignment.CenterEnd) // Align the icon to the end (right) of the box
                        .padding(end = 12.dp)
                )
            }

            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false },
                modifier = modifier
                    .background(Colors.DARK_GRAY)
                    ,
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        modifier = modifier
                            .clip(RoundedCornerShape(12.dp)),
                        onClick = {
                            selectedOption.value = option
                            expanded.value = false
                            onSelectedOption(option)
                        },
                        text = {
                            Text(
                                text = option,
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    fontFamily = Fonts.INTER,
                                    color = Colors.WHITE
                                )
                            )
                        }
                    )
                }
            }
        }
    }

}




@Composable
@Preview
fun PreviewSwitchBlock() {
    val options = arrayOf("Nimbus client", "Helios Client")
    Column (

        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()//
    ){
        ethOSSwitchBlock(
            modifier = Modifier.width(360.dp),
            options = options,
            onSelectedOption = {},
            title = "Light client",
            hasTitle = true,
            icon = {
                IconButton(
                    modifier = Modifier.size(16.dp),
                    onClick = {}//}
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Info,
                        contentDescription = "Information",
                        tint = Color(0xFF9FA2A5),
                        modifier = Modifier
                            .clip(CircleShape)
                        //.background(Color.Red)
                    )
                }
            }
        )
    }

}
