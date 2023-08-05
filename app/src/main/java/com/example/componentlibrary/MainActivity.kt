package com.example.componentlibrary

import android.os.Bundle
import android.view.Surface
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.componentlibrary.ui.components.SnackbarState
import com.example.componentlibrary.ui.components.ethOSButton
import com.example.componentlibrary.ui.components.ethOSDropDownMenu
import com.example.componentlibrary.ui.components.ethOSSnackbarHost
import com.example.componentlibrary.ui.components.ethOSTextfield
import com.example.componentlibrary.ui.components.rememberDropdownStateHolder
import com.example.componentlibrary.ui.components.rememberSnackbarDelegate
import com.example.componentlibrary.ui.theme.ethOSTheme

import com.example.componentlibrary.ui.theme.error
import kotlinx.coroutines.launch

//Composables
//import com.example.componentlibrary.ui.components.ethOSTextfield
//import com.example.componentlibrary.ui.components.rememberSnackbarDelegate

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



@Preview(showBackground = true, widthDp = 390, heightDp = 800)
@Composable
fun GreetingPreview() {
    ethOSTheme {

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