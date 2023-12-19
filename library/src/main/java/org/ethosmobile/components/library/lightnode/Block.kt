package org.ethosmobile.components.library.lightnode

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.ethosmobile.components.library.theme.Colors


@Composable
fun Block(
    number: String,
    tx: String,
    gas: String,
    onClick: () -> Unit
) {
    Box(
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment= Alignment.CenterVertically,

            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
        ) {

            Text(
                text = ""+number,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = Colors.WHITE,
                modifier = Modifier.weight(.5f),
                textAlign = TextAlign.Start

            )
            Text(
                text = tx,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                color = Colors.GRAY,
                modifier = Modifier.weight(.3f),
                textAlign = TextAlign.Center

            )
            Text(text = gas,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                color = Colors.GRAY,
                modifier = Modifier.weight(.3f),
                textAlign = TextAlign.Center
            )
            IconButton(
                onClick = onClick,
                modifier = Modifier.size(18.dp)
            ) {
                Box(
                    modifier = Modifier.weight(.1f),
                    contentAlignment = Alignment.CenterEnd
                ){
                    Icon(imageVector = Icons.Outlined.KeyboardArrowRight, contentDescription = "Block Details", tint = Colors.GRAY)
                }
            }


        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewBlock(
) {
    Column(
        modifier = Modifier.background(Color.Red)
    ) {
        Block("15949919","189", "0.13165") {

        }
    }

}