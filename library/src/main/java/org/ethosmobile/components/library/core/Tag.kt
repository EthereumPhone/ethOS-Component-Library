package org.ethosmobile.components.library.core

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
import org.ethosmobile.components.library.theme.Colors


@Composable
fun ethOSTag(
    header: String,
    primary: Boolean = true
){
    var background = if(primary) Colors.WHITE else Colors.BLUE;
    var textColor = if(primary) Colors.BLUE else Colors.WHITE;
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