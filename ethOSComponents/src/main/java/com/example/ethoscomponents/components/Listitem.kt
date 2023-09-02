package com.example.ethoscomponents.components

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ethoscomponents.R
import com.example.ethoscomponents.components.ethOSTag
import com.example.ethoscomponents.theme.Color
import androidx.compose.ui.graphics.Color as GraphicsColor


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
    header: String,
    subheader: String,
    chatListItem: Boolean,
    backgroundColor: GraphicsColor = Color.BLUE,
    colorOnBackground: GraphicsColor = Color.WHITE,
    msgnumber: Int

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
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.LIGHT_BLUE)
                    .size(64.dp)
            ) {
                Image(
                    painter = painterResource(id = drawableRes),
                    contentDescription = "",
                    modifier = Modifier
                        .size(48.dp),
                    colorFilter = ColorFilter.tint(Color.GRAY)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))
            Column() {
                Text(
                    text = header,
                    color = colorOnBackground,
                    style = MaterialTheme.typography.h5
                )
                if(chatListItem){
                    Text(
                        text = subheader,
                        color = Color.GRAY,
                        style = MaterialTheme.typography.body2
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
                    color = Color.GRAY,
                    style = MaterialTheme.typography.body2
                )
                ethOSTag(header = ""+msgnumber, primary = true)

            }
        }

    }
}

/*@Composable
@Preview
fun ethOSListItemPreview() {
    ethOSTheme() {
        ethOSListItem(R.drawable.baseline_check_24,
            "Header",
        "Subheader",
            chatListItem = true,
            msgnumber = 90
        )
    }
}*/
