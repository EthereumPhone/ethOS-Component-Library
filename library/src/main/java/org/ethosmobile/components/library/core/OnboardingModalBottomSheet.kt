package org.ethosmobile.components.library.core



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material.icons.rounded.SwapVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.ethosmobile.components.library.R
import org.ethosmobile.components.library.models.OnboardingItem
import org.ethosmobile.components.library.models.OnboardingObject
import org.ethosmobile.components.library.theme.Colors
import org.ethosmobile.components.library.theme.Fonts


@Composable
fun WelcomeModalBottomSheet(onDismiss: () -> Unit, onboardingObject: OnboardingObject) {

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(42.dp),
        modifier = Modifier.padding(start = 32.dp,end = 32.dp, bottom =32.dp)
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(painterResource(id = onboardingObject.imageVector), contentDescription = onboardingObject.title,tint = Colors.WHITE, modifier = Modifier.size(56.dp))
            Text(
                text = onboardingObject.title,
                textAlign = TextAlign.Center,
                fontSize = 32.sp,
                color = Colors.WHITE,
                fontWeight = FontWeight.SemiBold
            )
        }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(42.dp)
        ){

            onboardingObject.items.forEach { item ->
                item{
                    OnboardingListItem(item)
                }
            }
        }
        ethOSButton(text = "Continue", enabled = true, onClick = onDismiss)
    }
}






@Composable
fun OnboardingListItem(item: OnboardingItem){
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = item.imageVector,
            contentDescription = item.title,
            tint = Colors.WHITE,

            modifier = Modifier.size(42.dp)
        )
        Text(
            text = item.title,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            color = Colors.WHITE,
            fontFamily = Fonts.INTER,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = item.subtitle,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            fontFamily = Fonts.INTER,
            color = Colors.GRAY,
            fontWeight = FontWeight.SemiBold
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ethOSOnboardingModalBottomSheet(onDismiss: () -> Unit, sheetState: SheetState, onboardingObject: OnboardingObject){

    ModalBottomSheet(
        containerColor= Colors.BLACK,
        contentColor= Colors.WHITE,

        onDismissRequest = onDismiss,
        sheetState = sheetState
    ) {

        WelcomeModalBottomSheet(onDismiss, onboardingObject)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun ethOSOnboardingModalBottomSheetPreview() {
    val modalSheetState = rememberModalBottomSheetState(true)
    val coroutineScope = rememberCoroutineScope()

//    ethOSOnboardingModalBottomSheet(
//        onDismiss = {
//            coroutineScope.launch {
//                modalSheetState.hide()
//            }.invokeOnCompletion {
//
//            }
//        },
//        sheetState = modalSheetState
//    )

}

//Different between native apps
//val onboardingItems = listOf(
//    OnboardingItem(
//        imageVector = Icons.Filled.Wallet,
//        title= "Generated System Wallet",
//        subtitle = "Your personal ethOS address is managed by the wallet manager when connecting to ethOS native apps."
//    ),
//    OnboardingItem(
//        imageVector = Icons.Outlined.Send,
//        title= "Send & Receive Crypto",
//        subtitle = "Seamlessly send and receive ethereum across chains through the system wallet."
//    ),
//    OnboardingItem(
//        imageVector = Icons.Rounded.SwapVert,
//        title= "Swap Tokens",
//        subtitle = "Swap any ERC-20 Token directly on your phone."
//    )
//
//)



