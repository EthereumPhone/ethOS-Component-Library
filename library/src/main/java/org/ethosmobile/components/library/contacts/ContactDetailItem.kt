package org.ethosmobile.components.library.contacts

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.ethosmobile.components.library.haptics.EthOSHaptics

@Composable
fun ethOSContactDetailItem(title: String, detail: String, view: View){
    val interactionSource = remember { MutableInteractionSource() }
    //when user hovers over ContactListItem
    val isHover by interactionSource.collectIsHoveredAsState()
    val context = LocalContext.current

    val urlIntent = when(title){
        "ENS" -> Intent(
            Intent.ACTION_VIEW,
            Uri.parse(
                "https://etherscan.io/name-lookup-search?id=${detail}"
            )
        )

        "Ethereum Address" -> Intent(
            Intent.ACTION_VIEW,
            Uri.parse(
                "https://etherscan.io/address/${detail}"
            )
        )
        else -> null

    }

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                if (urlIntent !== null) {
                    view.performHapticFeedback(EthOSHaptics().NEUTRAL_HAPTIC)
                    context.startActivity(urlIntent)
                }
            }
            .background(
                color = if (isHover) {
                    Color(0xFF1a1a1a)
                } else {
                    Color.Transparent
                }
            )
            .padding(vertical = 12.dp, horizontal = 0.dp),
        horizontalArrangement = Arrangement.spacedBy(18.dp),
        verticalAlignment = Alignment.CenterVertically

    ){

        Column (
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Text(
                text = title,
                fontSize = 19.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Gray
            )

            Text(
                text = detail,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }
    }
}

@Composable
@Preview
fun PreviewContactDetailItem(){
    ethOSContactDetailItem("Mobile", "1234567890", LocalView.current)
}