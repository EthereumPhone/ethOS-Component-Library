package org.ethosmobile.components.library.lightnode

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.ethosmobile.components.library.theme.Font

@Composable
fun SwitchBlock(
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
        modifier = Modifier
            .fillMaxWidth()
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
                    color = Color(0xFF9FA2A5)
                )
                Spacer(modifier = Modifier.width(8.dp))
                icon()
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color.Black)
        ) {

            Box(
                modifier = Modifier.clickable {
                    expanded.value = !expanded.value }
                    .fillMaxWidth()
            ) {
                Text(
                    text = selectedOption.value,
                    color = Color.White,
                    lineHeight = 109.sp,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = Font.INTER
                    ),
                    modifier = Modifier.padding(vertical = 16.dp)
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.align(Alignment.CenterEnd) // Align the icon to the end (right) of the box
                        .padding(end = 12.dp)
                )
            }

            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false },
                modifier = Modifier
                    //.padding(horizontal = 24.dp)
                    .background(Color(0xFF262626))
                    .width(360.dp)
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        onClick = {
                            selectedOption.value = option
                            expanded.value = false
                            onSelectedOption(option)
                        }
                    ) {
                        Text(text = option,
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = Font.INTER
                            )
                        )
                    }
                }
            }
        }
    }

}