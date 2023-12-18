package org.ethosmobile.components.library.core

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.ethosmobile.components.library.R
import androidx.compose.ui.graphics.Color as GraphicsColor
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.ethosmobile.components.library.theme.Colors
import org.ethosmobile.components.library.theme.Font

/*@Composable
@Preview
fun ethOSListItemsPreview() {
    ethOSTheme {
        LazyColumn {
            // Add 5 items
            items(5) { index ->
                //Text(text = "Item: $index")
                ethOSListItem(
                    R.drawable.baseline_person_outline_24,
                    "Item: $index",
                    "Subheader-Item: $index",
                    chatListItem = false,
                    msgnumber = 90

                )
            }
        }
    }
}*/


@Composable
fun ethOSListItem(
    @DrawableRes drawableRes: Int = R.drawable.baseline_person_outline_24,
    header: String = "Header",
    subheader: String = "Subheader",
    chatListItem: Boolean = true,
    backgroundColor: Color = Colors.TRANSPARENT,
    colorOnBackground: Color = Colors.WHITE,
    msgnumber: Int = 0

) {
    Row(
        modifier = Modifier
            .background(backgroundColor)
            .padding(horizontal = 14.dp, vertical = 8.dp)
            .fillMaxWidth()

        ,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Colors.DARK_GRAY)
                    .size(64.dp)
            ) {
                Image(
                    painter = painterResource(id = drawableRes),
                    contentDescription = "",
                    modifier = Modifier
                        .size(36.dp),
                    colorFilter = ColorFilter.tint(Colors.GRAY)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))
            Column() {
                Text(
                    text = header,
                    color = colorOnBackground,
                    style = TextStyle(
                        color = Colors.WHITE,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = Font.INTER,
                    )
                )
                if(chatListItem){
                    Text(
                        text = subheader,
                        color = Colors.GRAY,
                        style = TextStyle(
                            color = Colors.GRAY,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = Font.INTER,
                        )
                    )
                }

            }
        }

        if(chatListItem){
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "0:00AM",
                    color = Colors.GRAY,
                    style = TextStyle(
                        color = Colors.GRAY,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = Font.INTER,
                    )
                )
                ethOSTag(header = ""+msgnumber, primary = true)

            }
        }

    }
}


@Composable
fun ModalListItem(
    headlineContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    supportingContent: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null
) {
    ListItem(
        headlineContent = headlineContent,
        modifier = modifier,
        supportingContent = supportingContent,
        trailingContent = trailingContent,
        colors = ListItemDefaults.colors(
            headlineColor = Colors.WHITE,
            supportingColor = Colors.GRAY,
            containerColor = Color.Transparent
        )
    )
}

@Preview
@Composable
fun PreviewWmListItem() {
    var text by remember { mutableStateOf("123123") }

//    ModalListItem(
//        {
//            Row {
//                Text(
//                    text = text,
//                    modifier = Modifier.weight(1f)
//                )
//                Column {
//
//                }
//
//                Text(text)
//            }
//
//        },
//    )

    ethOSListItem()
}

