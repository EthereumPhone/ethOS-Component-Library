package org.ethosmobile.components.library.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.ethosmobile.components.library.theme.Colors


@Composable
fun ethOSTag(
    header: String,
    primary: Boolean = true,
    size: Int = 14
){
    var background = if(primary) Colors.WHITE else Colors.BLACK;
    var textColor = if(primary) Colors.BLACK else Colors.WHITE;
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(CircleShape)
            .background(background)
            .padding(8.dp, 4.dp)
    ) {
        Text(
            text = header,
            color = textColor,
            style = TextStyle(
                fontWeight = FontWeight.SemiBold,
                fontSize = size.sp,
            )
        )
    }
}

@Composable
@Preview
fun PreviewEthOSTag(){
    ethOSTag(header = "Cammera")
}