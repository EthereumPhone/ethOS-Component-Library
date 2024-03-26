package org.ethosmobile.components.library.core



import android.annotation.SuppressLint

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.ColumnScopeInstance.align
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.ethosmobile.components.library.R
import org.ethosmobile.components.library.utils.SnackbarDelegate
import org.ethosmobile.components.library.utils.SnackbarState
import org.ethosmobile.components.library.utils.rememberSnackbarDelegate
import org.ethosmobile.components.library.theme.Colors
import org.ethosmobile.components.library.theme.Fonts
import org.ethosmobile.components.library.theme.ethOSTheme


@SuppressLint("SuspiciousIndentation")
@Composable
@Preview
fun ethOSSnackbarPreview() {
    ethOSTheme {
        val scaff = rememberScaffoldState()
        val scope = rememberCoroutineScope()
        val hostState = remember { SnackbarHostState() }
        val s = rememberSnackbarDelegate(hostState,scope)

        Scaffold(

            snackbarHost={
                ethOSSnackbarHost(delegate = s, modifier = Modifier.padding(12.dp))
            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Colors.BLACK)
                    //.padding(8.dp)
                    .padding(paddingValues = it)
            ) {

                Column(modifier = Modifier.fillMaxSize()) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            s.coroutineScope.launch {
                                s.showSnackbar(SnackbarState.DEFAULT,"DEFAULT")
                            }
                        }) {
                        Text("Show Default")
                    }
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            s.coroutineScope.launch {
                                s.showSnackbar(SnackbarState.SUCCESS,"SUCCESS")
                            }
                        }) {
                        Text("Show Success")
                    }
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            s.coroutineScope.launch {
                                s.showSnackbar(SnackbarState.WARNING,"WARNING")
                            }
                        }) {
                        Text("Show Warning")
                    }
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            s.coroutineScope.launch {
                                s.showSnackbar(SnackbarState.ERROR,"ERROR")
                            }
                        }) {
                        Text("Show Error")
                    }

                }
                /*SnackbarHost(
                    modifier=Modifier.align(Alignment.BottomStart),
                    hostState = snackState
                ) { snackbarData: SnackbarData ->
                    CustomSnackBar(
                        R.drawable.baseline_check_24,
                        snackbarData.message,
                        isRtl = true,
                        containerColor = white
                    )
                }*/
                }
        }


    }
}

@Composable
fun ethOSSnackBar(
    //delegate: SnackbarDelegate,
    snackbarOnColor: Color,
    snackbarBackgroundColor: Color,
    snackbarText: String,
    snackbarIcon: Int,


    //isRtl: Boolean = true,
    //containerColor: Color = white
) {
    Snackbar(
        contentColor = snackbarOnColor,
        shape =  RoundedCornerShape(12.dp),
        containerColor = snackbarBackgroundColor,
    ) {
        /*CompositionLocalProvider(
            LocalLayoutDirection provides
                    if (isRtl) LayoutDirection.Rtl else LayoutDirection.Ltr
        ) {*/
            Row (
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
                    ){
                Text(
                    text = snackbarText,
                    style = TextStyle(
                        color = snackbarOnColor,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = Fonts.INTER,
                    )
                )
                Image(
                    modifier = Modifier.size(24.dp),
                    colorFilter = ColorFilter.tint(snackbarOnColor),
                    painter = painterResource(id = snackbarIcon),
                    contentDescription = "Snackbar icon"
                )
            }
        //}
    }
}

@Composable
fun ethOSSnackbarHost(
    delegate: SnackbarDelegate,
    modifier: Modifier
) {
    ethOSTheme() {
        SnackbarHost(
            modifier= modifier,
            hostState = delegate.snackbarHostState
        ) { snackbarData: SnackbarData ->
            ethOSSnackBar(
                delegate.snackbarOnColor,
                delegate.snackbarBackgroundColor,
                delegate.snackbarText,
                delegate.snackbarIcon,
            ) }
    }
}




