package com.example.ethoscomponents.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ethOSNetworkPill(
    address: String,
    chainId: Int,
) {


    var network = ""
    when (chainId) {
        1 -> network = "Mainnet"
        5 -> network = "Goerli"
        10 -> network = "Optimism"
        137 -> network = "Polygon"
        42161 -> network = "Arbitrum"
        else -> {
            network = "Mainnet"
        }
    }

    var color = Color(0xFF2E3D4E)
    when (chainId) {
        1 -> color = Color(0xFF45BA4A) //Mainnet
        5 -> color = Color(0xFFFCA311)
        10 -> color = Color(0xFFE52222)
        137 -> color = Color(0xFF8A50DA) //Goerli
        42161 -> color = Color(0xFF49A9F2)
        else -> {
            color = Color(0xFF45BA4A)
        }
    }

    val text = network+" " + address.take(5) + "..."
    val context = LocalContext.current
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(RoundedCornerShape(50.dp))
            .background(Color(0xFF2E3D4E))
            .padding(vertical = 6.dp, horizontal = 12.dp)
            .clickable {
                val clipboard: ClipboardManager? =
                    context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?
                val clip = ClipData.newPlainText("Address", address)
                clipboard?.setPrimaryClip(clip)

                // Toast copy to clipboard
                Toast
                    .makeText(context, "Copied to clipboard", Toast.LENGTH_SHORT)
                    .show()

            }
    ) {
        Box (
            modifier = Modifier
                .clip(CircleShape)
                .background(color)
                .size(15.dp)
        ) {}
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            fontWeight = FontWeight.Normal,
            style = MaterialTheme.typography.button,
            color = Color.White
        )
    }
}


/*@Composable
@Preview
fun ethOSNetworkPillPreview() {
    ethOSTheme() {
        ethOSNetworkPill("0xefBABdeE59968641DC6E892e30C470c2b40157Cd",1)
    }
}*/