package org.ethosmobile.components.library.walletmanager

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import org.ethosmobile.components.library.theme.Fonts

@Composable
fun ethOSSimpleAssetListItem(
    title: String,
    value: String,
    fiatAmount: String = "\$TODO",
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title, fontFamily = Fonts.INTER, fontWeight = FontWeight.SemiBold, fontSize = 18.sp, modifier = Modifier.weight(0.4f), color = Color.White)
        Text(text = "${value.replace(",", ".").toDouble()}",fontFamily = Fonts.INTER, fontWeight = FontWeight.Medium, fontSize = 18.sp, textAlign = TextAlign.End, modifier = Modifier.weight(0.30f), color = Color.White )
        //Text(text = fiatAmount,fontFamily = Fonts.INTER, fontWeight = FontWeight.Medium, fontSize = 18.sp, textAlign = TextAlign.End, modifier = Modifier.weight(0.30f), color = Color(0xFF9FA2A5))
    }
}

@Preview
@Composable
fun ethOSSimpleAssetListItemPreview(){
    ethOSSimpleAssetListItem("Elie","0,1523")
}