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
import androidx.compose.ui.unit.sp

@Composable
fun SimpleAssetListItem(
    title: String,
    value: Double,
    fiatAmount: String = "\$TODO",
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title.uppercase(),  fontWeight = FontWeight.SemiBold, fontSize = 18.sp, modifier = Modifier.weight(0.4f), color = Color.White)
        Text(text = "${value}", fontWeight = FontWeight.Medium, fontSize = 18.sp, textAlign = TextAlign.End, modifier = Modifier.weight(0.30f), color = Color.White )
        Text(text = fiatAmount, fontWeight = FontWeight.Medium, fontSize = 18.sp, textAlign = TextAlign.End, modifier = Modifier.weight(0.30f), color = Color(0xFF9FA2A5))
    }
}