package org.ethosmobile.components.library.mint

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.ethosmobile.components.library.core.dashedBorder
import org.ethosmobile.components.library.models.TokenAsset
import org.ethosmobile.components.library.theme.Colors
import org.ethosmobile.components.library.theme.Fonts

@Composable
fun ImageBox(
    modifier: Modifier= Modifier,
    onClick: () -> Unit
) {

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .dashedBorder(
                4.dp,
                Colors.GRAY,
                shape= RoundedCornerShape(24.dp),
                on = 20.dp,
                off = 10.dp
            )
            .clickable {
                onClick()
            }


    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement= Arrangement.Center,
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    Icons.Outlined.Image,
                    contentDescription = "Select Image",
                    tint = Colors.GRAY,
                    modifier = Modifier
                        .size(36.dp)
                )
                Spacer(modifier = Modifier.width(18.dp))
                Text(
                    text = "Select Image",
                    fontSize = 18.sp,
                    color = Colors.GRAY,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = Fonts.INTER
                )
            }
        }
    }
}

@Composable
@Preview
fun ImageBoxPreview(
){
    Column (

        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.height(300.dp).fillMaxWidth().padding(12.dp)//
    ){
        ImageBox(onClick={})
    }

}