package org.ethosmobile.components.library.lightnode

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.ethosmobile.components.library.theme.Colors
import org.ethosmobile.components.library.theme.Font

@Composable
fun InfoBlock(
    modifier: Modifier = Modifier,
    text: String,
    title: String = "",
    hasTitle: Boolean,
    icon: @Composable () -> Unit,
) {
    Column (
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
            .fillMaxWidth()
    ){
        if(hasTitle){
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    //modifier = modifier.padding(start = 16.dp),
                    text = title,
                    fontSize = 16.sp,
                    color = Colors.WHITE
                )
                Spacer(modifier = Modifier.width(8.dp))
                icon()
            }
        }

        Column (
            modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Colors.TRANSPARENT)
        ){
            Text(
                text = text,
                color = Colors.WHITE,
                lineHeight = 109.sp,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = Font.INTER
                ),
                modifier = Modifier.padding(vertical = 16.dp)
            )

        }
    }


}

@Preview(showBackground = true)
@Composable
fun PreviewTinystatus(
) {
    InfoBlock(
        text = "Ethereum mainnet",
        title = "Network",
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