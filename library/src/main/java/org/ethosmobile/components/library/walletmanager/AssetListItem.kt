package org.ethosmobile.components.library.walletmanager

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.ethosmobile.components.library.models.TokenAsset
import org.ethosmobile.components.library.theme.Colors
import java.text.DecimalFormat

@Composable
fun AssetListItem(
    title: String,
    assets: List<TokenAsset>,
    linkTo: () -> Unit = {},
){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ){
        Text(title.uppercase(), color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ){
                Row (
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically,

                    ){
                    Text(formatDouble(assets.sumOf { it.balance }), color = Colors.WHITE,fontSize = 18.sp, fontWeight = FontWeight.Medium)

                    Text(assets.get(0).symbol.uppercase(), color = Colors.WHITE, fontSize = 18.sp, fontWeight = FontWeight.Medium)
                }
                Text("\$TODO", color = Colors.GRAY,fontSize = 18.sp, fontWeight = FontWeight.Medium)
            }
            Spacer(modifier = Modifier.width(24.dp))

            IconButton(
                onClick = linkTo,
                modifier = Modifier.size(32.dp),
            ) {
                Icon(
                    imageVector = Icons.Rounded.ArrowForwardIos,
                    contentDescription = "Forward",
                    tint = Colors.WHITE,
                    modifier = Modifier.size(16.dp)
                )
            }


        }
    }
}


@Composable
fun AssetListDetailItem(
    tokenAsset: TokenAsset,
){
    val networkname = when(tokenAsset.chainId){
        1 -> "Mainnet"
        5 -> "Görli"
        10 -> "Optimism"
        137 -> "Polygon"
        8453 -> "Base"
        42161 -> "Arbitrum"
        else -> { ""}
    }
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ){
        Text(networkname, color = Colors.WHITE, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ){
                Row (
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically,

                    ){
                    Text("${tokenAsset.balance}", color = Colors.WHITE,fontSize = 16.sp, fontWeight = FontWeight.Medium)

                    Text(tokenAsset.symbol.uppercase(), color = Colors.WHITE,fontSize = 16.sp, fontWeight = FontWeight.Medium)
                }
                Text("\$TODO", color = Colors.GRAY,fontSize = 16.sp,fontWeight = FontWeight.Medium )
            }

        }
    }
}

fun formatDouble(input: Double): String {
    val decimalFormat = DecimalFormat("#.#####")
    return decimalFormat.format(input)
}
