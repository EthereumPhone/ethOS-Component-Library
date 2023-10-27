package org.ethosmobile.components.demo

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Vibrator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.ethosmobile.components.library.core.SnackbarState
import org.ethosmobile.components.library.core.ethOSButton
import org.ethosmobile.components.library.core.ethOSDropDownMenu
import org.ethosmobile.components.library.core.ethOSSnackbarHost
import org.ethosmobile.components.library.core.ethOSTextfield
import org.ethosmobile.components.library.core.rememberDropdownStateHolder
import org.ethosmobile.components.library.core.rememberSnackbarDelegate
import org.ethosmobile.components.library.theme.ethOSTheme

import kotlinx.coroutines.launch
import org.ethosmobile.components.library.core.Haptics

//Composables
//import com.example.ethoscomponents.components.ethOSTextfield
//import com.example.ethoscomponents.components.rememberSnackbarDelegate

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ethOSTheme {
                // A surface container using the 'background' color from the theme
                /*Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")

                }*/
                GreetingPreview()
            }
        }
    }
}



@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, widthDp = 390, heightDp = 800)
@Composable
fun GreetingPreview() {
    ethOSTheme {


        //Before enables vibrate
        //Responsible for vibrate
        var vibrator =  LocalContext.current.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator


        val scope = rememberCoroutineScope()
        val hostState = remember { SnackbarHostState() }
        val sdeg = rememberSnackbarDelegate(hostState,scope)

        //Textfield
        Box() {
            Column (

                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Hallow")
            ethOSTextfield(value = "", placeholder = "Amount", modifier = Modifier.fillMaxWidth())
            ethOSTextfield(value = "", placeholder = "Amount", modifier = Modifier.fillMaxWidth())
                ethOSButton(
                    "Mint",
                    R.drawable.baseline_swap_vert_24,
                    true,
                    onClick = {
                        sdeg.coroutineScope.launch {
                            sdeg.showSnackbar(SnackbarState.SUCCESS,"DEFAULT")
                            vibrator.vibrate(Haptics().POSITIVE_HAPTIC)
                        }
                    },
                    primary = true)
                val items = (1..5).map {
                    "$it-ETH"
                }
                val s = rememberDropdownStateHolder(items)
                ethOSDropDownMenu(stateHolder = s)
            }
            ethOSSnackbarHost(delegate = sdeg, modifier = Modifier.padding(12.dp).align(alignment = Alignment.BottomStart))
        }
    }
}