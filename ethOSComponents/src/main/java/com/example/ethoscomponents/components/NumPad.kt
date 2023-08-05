package com.example.ethoscomponents.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size

import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.ui.tooling.preview.Preview
import com.example.ethoscomponents.util.IconAsText


@Composable
fun NumPad(
    value: String,
    contentColor: Color = NumPadDefaults.contentColor,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit
) {
    val numPadButtons = NumPadButtons.values().asList()

    LazyVerticalGrid(columns = GridCells.Fixed(3)){
        itemsIndexed(numPadButtons) { index, button ->
            val fixedIndex = (index+1).mod(11)
            Box(
                modifier = Modifier
                    .clickable {
                        when(button) {
                            NumPadButtons.COMMA -> {
                                if (!value.contains(".")) {
                                    if (value == "") {
                                        onValueChange("0.")
                                    } else {
                                        onValueChange("$value.")
                                    }
                                }
                            }
                            NumPadButtons.UNDO -> onValueChange(value.dropLast(1))
                            else -> onValueChange(value + fixedIndex.toString())
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                var input = when(button) {
                    NumPadButtons.UNDO -> Icons.Rounded.ArrowBack
                    NumPadButtons.COMMA -> "·"
                    else -> fixedIndex.toString()
                }

                if(input is ImageVector) {
                    Box(
                        modifier = Modifier.size(32.dp)
                    ) {
                        IconAsText(
                            icon = input,
                            modifier = Modifier.align(Alignment.BottomCenter)
                        )
                    }
                }
                if(input is String) {
                    Text(
                        text = input,
                        fontSize = NumPadDefaults.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = contentColor,
                    )
                }
            }
        }
    }


}


object NumPadDefaults {
    val contentColor = Color.White
    val fontSize = 24.sp
}

@Preview
@Composable
fun PreviewNumPad() {
    NumPad(
        value = "",
        modifier = Modifier
    ) {}
}