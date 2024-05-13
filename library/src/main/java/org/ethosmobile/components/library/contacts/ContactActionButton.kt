package org.ethosmobile.components.library.contacts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.ethosmobile.components.library.theme.Colors

@Composable
fun ContactDetailActionButton(
    icon: Int,
    text: String,
    onClick: () -> Unit,
    //view: View,


    ){

    Column (
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(
            onClick = {
                //view.performHapticFeedback(Haptics().NEUTRAL_HAPTIC)
                onClick()
            },
        ) {
//            Column (
//                modifier = Modifier,//.wrapContentHeight().wrapContentWidth(),//.background(Colors.ERROR),
//                verticalArrangement = Arrangement.spacedBy(6.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
            Icon(painter = painterResource(id = icon), contentDescription = "", modifier = Modifier.size(28.dp), tint = Colors.WHITE)
//                Text(
//                    //modifier = Modifier.wrapContentHeight().wrapContentWidth(),//.background(Colors.ERROR),
//                    text = text,
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.Medium,
//                    color = Colors.WHITE,
//
//                )


        }
        Text(
            modifier = Modifier.clickable {
                //view.performHapticFeedback(Haptics().NEUTRAL_HAPTIC)
                onClick()
            },
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Colors.WHITE
        )


    }
}