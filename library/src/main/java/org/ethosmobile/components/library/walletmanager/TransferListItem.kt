package org.ethosmobile.components.library.walletmanager

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDownward
import androidx.compose.material.icons.rounded.ArrowForwardIos
import androidx.compose.material.icons.rounded.NorthEast
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.ethosmobile.components.library.models.TransferItem
import org.ethosmobile.components.library.theme.Colors
import org.ethosmobile.components.library.theme.Fonts

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ethOSTransferListItem(
    modifier: Modifier = Modifier,
    chainId: Int,
    from: String,
    to: String,
    asset: String,
    value: String,
    timeStamp: String,
    userSent: Boolean,
    txHash: String,
    fiatAmount: String = "\$TODO",
    onCardClick: () -> Unit = {}
) {

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(24.dp),
    ) {

        //Color of Icon
        var icontint = if (userSent) Colors.ERROR else Colors.SUCCESS

        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {

            Column (
                modifier = modifier
            ){
                Text(

                    text = if(userSent) "Sent ${asset}" else "Received ${asset}",
                    color = Colors.WHITE,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    fontFamily = Fonts.INTER,
                    modifier = Modifier.width(125.dp)
                )
                Text(timeStamp.take(10), color = Colors.GRAY, fontFamily = Fonts.INTER,fontSize = 18.sp, fontWeight = FontWeight.Medium)
            }

            Row (
                verticalAlignment = Alignment.CenterVertically,
            ){
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center
                ){
                    Row (
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        ){
                        Text(
                            text = "${if(userSent) "-" else "+"}${value}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = Fonts.INTER,
                            color = icontint
                        )
                        Text(asset, fontSize = 18.sp, fontFamily = Fonts.INTER,fontWeight = FontWeight.Medium, color = icontint)
                    }
                    //Text(fiatAmount, color = Colors.GRAY, fontSize = 18.sp, fontWeight = FontWeight.Medium )
                }

                Spacer(modifier = Modifier.width(24.dp))

                IconButton(
                    onClick = onCardClick,
                    modifier = Modifier.size(32.dp),
                ) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowForwardIos,
                        contentDescription = "Go back",
                        tint = Colors.WHITE,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }


    }



}
@Composable
@Preview
fun TransferListItemPreview(
) {
    Column(
        modifier = Modifier.fillMaxSize().background(Colors.BLACK).padding(24.dp)
    ) {
        ethOSTransferListItem(
            fiatAmount = "$0.00",

                chainId = 5,
                from = "0xdf5c7149d624D7bEac667edF6688bb89ED80cf73",
                to =  "0xdf5c7149d624D7bEac667edF6688bb89ED80cf73",
                asset = "ETH",
                value = "2.24",
                timeStamp = "10-19-01",//Clock.System.now().toString(),
                userSent = false,
                txHash= "pfpfnopjfpfn",

            onCardClick = {}
        )
    }

}