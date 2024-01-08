package org.ethosmobile.components.library.core

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import org.ethosmobile.components.library.theme.Colors
import org.ethosmobile.components.library.theme.Fonts

@Composable
fun ethOSInfoDialog(
    title: String,
    text: String,
    setShowDialog: () -> Unit,
){
    Dialog(onDismissRequest = { setShowDialog() }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Colors.BLACK,
            contentColor = Colors.WHITE,
            border = BorderStroke(width = 1.dp, Colors.WHITE),
            elevation = 2.dp
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.padding(28.dp),

                    horizontalAlignment = Alignment.CenterHorizontally

                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = title,
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontFamily = Fonts.INTER,
                                fontWeight = FontWeight.SemiBold
                            )
                        )

                    }
                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = text,
                        fontSize = 16.sp,
                        color = Colors.GRAY,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Normal,
                        fontFamily = Fonts.INTER,
                    )

                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewInfoDialog() {
    ethOSInfoDialog(
        "Title",
        "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.",
        {}
    )
}