package com.example.ethoscomponents.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.ethoscomponents.theme.Color


@Composable
fun ethOSTag(
    header: String,
    primary: Boolean = true
){
    var background = if(primary) Color.WHITE else Color.BLUE;
    var textColor = if(primary) Color.BLUE else Color.WHITE;
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.clip(RoundedCornerShape(4.dp)).background(background).padding(2.dp)
    ) {
        Text(
            text = header,
            color = textColor,
            style = MaterialTheme.typography.button
        )
    }
}