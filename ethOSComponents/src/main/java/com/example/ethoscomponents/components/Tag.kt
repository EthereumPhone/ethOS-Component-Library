package com.example.componentlibrary.ui.components

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
import com.example.componentlibrary.ui.theme.blue
import com.example.componentlibrary.ui.theme.white


@Composable
fun ethOSTag(
    header: String,
    primary: Boolean = true
){
    var background = if(primary) white else blue;
    var textColor = if(primary) blue else white;
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