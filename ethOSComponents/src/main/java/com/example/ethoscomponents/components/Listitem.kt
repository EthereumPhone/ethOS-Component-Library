package com.example.componentlibrary.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarData
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.componentlibrary.ui.theme.blue
import com.example.componentlibrary.ui.theme.ethOSTheme
import com.example.componentlibrary.ui.theme.gray
import com.example.componentlibrary.ui.theme.lightblue
import com.example.componentlibrary.ui.theme.white
import com.example.ethoscomponents.R
import kotlinx.coroutines.launch

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
    backgroundColor: Color = blue,
    colorOnBackground: Color = white,
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
                    .background(lightblue)
                    .size(64.dp)
            ) {
                Image(
                    painter = painterResource(id = drawableRes),
                    contentDescription = "",
                    modifier = Modifier
                        .size(48.dp),
                    colorFilter = ColorFilter.tint(gray)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))
            Column {
                Text(
                    text = header,
                    color = colorOnBackground,
                    style = MaterialTheme.typography.h5
                )
                if(chatListItem){
                    Text(
                        text = subheader,
                        color = gray,
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
                    color = gray,
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
