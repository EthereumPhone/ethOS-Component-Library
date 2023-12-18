package org.ethosmobile.components.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.ethosmobile.components.library.core.SnackbarState
import org.ethosmobile.components.library.core.ethOSButton
import org.ethosmobile.components.library.core.ethOSDropDownMenu
import org.ethosmobile.components.library.core.ethOSSnackbarHost
//import org.ethosmobile.components.library.core.ethOSTextfield
import org.ethosmobile.components.library.core.rememberDropdownStateHolder
import org.ethosmobile.components.library.core.rememberSnackbarDelegate
import org.ethosmobile.components.library.theme.ethOSTheme

import kotlinx.coroutines.launch
import org.ethosmobile.components.library.core.Header
import org.ethosmobile.components.library.core.ethOSIconButton
import org.ethosmobile.components.library.core.ethOSTagButton
import org.ethosmobile.components.library.models.TokenAsset
import org.ethosmobile.components.library.models.TransferItem
import org.ethosmobile.components.library.theme.Colors
import org.ethosmobile.components.library.walletmanager.AssetListDetailItem
import org.ethosmobile.components.library.walletmanager.AssetListItem
import org.ethosmobile.components.library.walletmanager.SimpleAssetListItem
import org.ethosmobile.components.library.walletmanager.TransferListItem

//Composables
//import com.example.ethoscomponents.components.ethOSTextfield
//import com.example.ethoscomponents.components.rememberSnackbarDelegate

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ethOSTheme {
                // A surface container using the 'background' color from the theme
               Column (
                   modifier = Modifier
                       .fillMaxSize()
                       .background(Colors.BLACK),
                   horizontalAlignment = Alignment.CenterHorizontally
               ){
                   Header(
                       title = "Send",
                       isBackButton = false,
                       isTrailContent = true,
                       trailContent = {
                           IconButton(
                                onClick = {  },
                                //modifier=Modifier.background(Color.Red)
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Info,
                                    contentDescription = "Go back",
                                    tint =  Colors.WHITE,
                                    modifier = Modifier.size(32.dp)
                                )
                            }
                       },
                       isBottomContent = false,
                       bottomContent = {
                                       Text(
                                            text = "on Mainnet",
                                            color= Colors.GRAY,
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.Medium
                                        )
                       }
                   )
                   TokenAsset(
                        address = "0xdf5c7149d624D7bEac667edF6688bb89ED80cf73",
                        chainId = 1,
                        symbol = "ETH",
                        name = "Ether",
                        balance = 0.61
                   )


        TransferItem(
            chainId = 5,
            from = "0xdf5c7149d624D7bEac667edF6688bb89ED80cf73",
            to =  "0x8A5E7Ccc2de3D1567C4DDff348678A041ed2e2eB",
            asset = "ETH",
            value = "2.24",
            timeStamp = "1000-19-01 00:00:00",//Clock.System.now().toString(),
            userSent = true,
            txHash= "0x3fb683c2e61495e26a3159bbefd83d83bd2a70dcebe7d884e4200fcc546714c5",
        )

                   Column (
                       modifier = Modifier.padding(horizontal = 32.dp),
                       verticalArrangement = Arrangement.spacedBy(32.dp),
                       horizontalAlignment = Alignment.CenterHorizontally
                   ){
                       SimpleAssetListItem(title = "Mainnet", value = 0.61)
                       AssetListItem(title = "ETH" ,assets = listOf(
                           TokenAsset(
                               address = "0xdf5c7149d624D7bEac667edF6688bb89ED80cf73",
                               chainId = 1,
                               symbol = "ETH",
                               name = "Ether",
                               balance = 0.61
                           ),
                           TokenAsset(
                               address = "0xdf5c7149d624D7bEac667edF6688bb89ED80cf73",
                               chainId = 10,
                               symbol = "ETH",
                               name = "Ether",
                               balance = 0.21
                           )
                       ))
                       AssetListDetailItem(tokenAsset = TokenAsset(
                           address = "0xdf5c7149d624D7bEac667edF6688bb89ED80cf73",
                           chainId = 1,
                           symbol = "ETH",
                           name = "Ether",
                           balance = 0.61
                       ))
                       TransferListItem(
                           transfer= TransferItem(
                               chainId = 5,
                               from = "0xdf5c7149d624D7bEac667edF6688bb89ED80cf73",
                               to =  "0xdf5c7149d624D7bEac667edF6688bb89ED80cf73",
                               asset = "ETH",
                               value = "2.24",
                               timeStamp = "10-19-01",//Clock.System.now().toString(),
                               userSent = true,
                               txHash= "pfpfnopjfpfn",
                           ),
                           onCardClick = {}
                       )

                           Row(
                               horizontalArrangement = Arrangement.spacedBy(32.dp),
                           ){
                               ethOSIconButton(onClick = { /*TODO*/ }, icon = Icons.Rounded.Favorite, contentDescription = "Favorite")
                               ethOSIconButton(onClick = { /*TODO*/ }, icon = Icons.Rounded.Person, contentDescription = "Person")
                               ethOSIconButton(onClick = { /*TODO*/ }, icon = Icons.Rounded.Call, contentDescription = "Call")
                           }


                       ethOSTagButton(
                          "ETH",
                           onClick = {}
                       )
                   }
               }
            }
        }
    }
}



@Preview(showBackground = true, widthDp = 390, heightDp = 800)
@Composable
fun GreetingPreview() {
    ethOSTheme {



        val scope = rememberCoroutineScope()
        val hostState = remember { SnackbarHostState() }
        val sdeg = rememberSnackbarDelegate(hostState,scope)

        //Textfield

    }
}