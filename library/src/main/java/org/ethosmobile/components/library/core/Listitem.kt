package org.ethosmobile.components.library.core

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import org.ethosmobile.components.library.theme.Fonts

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
    withImage:Boolean = false,
    image: @Composable () -> Unit = {},
    header: String = "Header",
    isSubheader: Boolean = false,
    subheader: String = "Subheader",
    trailingContent: @Composable (() -> Unit)? = null,
    backgroundColor: Color = Colors.TRANSPARENT,
    colorOnBackground: Color = Colors.WHITE,
    subheaderColorOnBackground: Color = Colors.WHITE,
    onClick: () -> Unit = {},
    modifier: Modifier= Modifier

) {


    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.clickable { onClick() },
    ) {
        if(withImage){
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
                    .clip(CircleShape)
                    .background(Colors.DARK_GRAY)
                    .size(64.dp)
            ) {
                image()
            }
        }

        ListItem(
            headlineContent = {
                Text(
                    text = header,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = Colors.WHITE
                )
            },
            supportingContent = {
                if(isSubheader){
                    Text(
                        text = subheader,
                        color = Colors.GRAY
                    )
                }

            },
            trailingContent = trailingContent,

            colors = ListItemDefaults.colors(
                headlineColor = colorOnBackground,
                supportingColor = subheaderColorOnBackground,
                containerColor = backgroundColor
            )
        )

    }

}



@Composable
fun ethOSCustomListItem(
    headlineContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    supportingContent: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    backgroundColor: Color = Colors.TRANSPARENT,
    colorOnBackground: Color = Colors.WHITE,
    subheaderColorOnBackground: Color = Colors.GRAY,
    onClick: () -> Unit = {}
) {
    ListItem(
        headlineContent = headlineContent,
        modifier = modifier.clickable { onClick() },
        supportingContent = supportingContent,
        trailingContent = trailingContent,
        colors = ListItemDefaults.colors(
            headlineColor = colorOnBackground,
            supportingColor = subheaderColorOnBackground,
            containerColor = backgroundColor
        )
    )
}


@Composable
fun ethOSChatListItem(
    image: @Composable () -> Unit = {},
    header: String = "Header",
    subheader: String = "Subheader",
    time: String = "0:00AM",
    backgroundColor: Color = Colors.TRANSPARENT,
    colorOnBackground: Color = Colors.WHITE,
    subheaderColorOnBackground: Color = Colors.WHITE,
    msgnumber: Int = 0,
    onClick: () -> Unit={},


) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { onClick() },
    ){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(CircleShape)
                .background(Colors.DARK_GRAY)
                .size(64.dp)
        ) {
            image()
        }
        ListItem(
            headlineContent = {
                Text(
                    text = header,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            },
            supportingContent = {
                Text(
                    text = subheader,
                    color = Colors.GRAY
                )
            },
            trailingContent = {
                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = time,
                        color = Colors.GRAY,
                        style = TextStyle(
                            color = Colors.GRAY,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = Fonts.INTER,
                        )
                    )
                    ethOSTag(header = ""+msgnumber, primary = true)

                }
            },


            colors = ListItemDefaults.colors(
                headlineColor = colorOnBackground,
                supportingColor = subheaderColorOnBackground,
                containerColor = backgroundColor
            )
        )
    }

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

    ethOSListItem(isSubheader = true, trailingContent = { Text(text = "Hallow",fontSize = 20.sp,
        fontWeight = FontWeight.Medium,
        color = Color.White)})
}

