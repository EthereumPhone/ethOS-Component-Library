package org.ethosmobile.components.library.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.ethosmobile.components.library.theme.Colors

@Composable
fun Header(
    modifier: Modifier = Modifier,
    title: String,
    isBackButton: Boolean = false,
    onBackClick: () -> Unit = {},
    isTrailContent: Boolean = false,
    trailContent: @Composable () -> Unit = {},
    isBottomContent: Boolean = false,
    bottomContent: @Composable () -> Unit = {}//String = "",

) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(Colors.BLACK)
            .padding(vertical = 24.dp, horizontal = 24.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = if (isBackButton || isTrailContent) Arrangement.SpaceBetween else Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.width(if (isBackButton || isTrailContent) 64.dp else 10.dp)


            ) {

                if(isBackButton){
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBackIosNew,
                            contentDescription = "Go back",
                            tint =  Colors.WHITE,
                            modifier = modifier.size(32.dp)
                        )
                    }
                }


            }

            //Header title
            Text(
                modifier = modifier.weight(1f),
                textAlign = TextAlign.Center,
                text = title,
                fontSize = 28.sp,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )

//            Warning or info
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.width(if (isBackButton || isTrailContent) 64.dp else 10.dp)


            ) {

                if (isTrailContent){
                    trailContent()
                }
//                IconButton(
//                    onClick = onTrailIconClick,
//                    //modifier=Modifier.background(Color.Red)
//                ) {
//                    Icon(
//                        imageVector = trailIcon,
//                        contentDescription = "Go back",
//                        tint =  Colors.WHITE,
//                        modifier = modifier.size(32.dp)
//                    )
//                }
            }
        }
        if (isBottomContent){
//            Text(
//                text = bottomText,
//                color= Colors.GRAY,
//                fontSize = 18.sp,
//                fontWeight = FontWeight.Medium
//            )
            bottomContent()
        }


    }
    //Divider(color = Colors.DARK_GRAY)
}


@Preview
@Composable
fun PreviewHeader() {
//    Header(
//        title = "Send",
//        isBackButton = true,
//        isTrailContent = false,
//        onTrailIconClick = {},
//        isBottomContent = true,
//        bottomContent = {  }
//    )
}