package org.ethosmobile.components.library.core



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
import androidx.compose.material3.MaterialTheme
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
import org.ethosmobile.components.library.theme.Colors
import org.ethosmobile.components.library.theme.Font
import org.ethosmobile.components.library.theme.ethOSTheme


@Composable
@Preview
fun ethOSSnackbarPreview() {
    ethOSTheme {
        val scaff = rememberScaffoldState()
        val scope = rememberCoroutineScope()
        val hostState = remember { SnackbarHostState() }
        val s = rememberSnackbarDelegate(hostState,scope)

        /*Scaffold(
            scaffoldState= scaff,
        ) {*/
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Colors.BLACK)
                    //.padding(8.dp)
                    //.padding(paddingValues = it)
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
                ethOSSnackbarHost(delegate = s, modifier = Modifier.padding(12.dp).align(alignment = Alignment.BottomStart))
            }
        //}


    }
}

@Composable
fun CustomSnackBar(
    @DrawableRes drawableRes: Int,
    delegate: SnackbarDelegate,
    message: String,
    //isRtl: Boolean = true,
    //containerColor: Color = white
) {
    Snackbar(
        contentColor = delegate.snackbarOnColor,
        shape =  RoundedCornerShape(12.dp),
        containerColor = delegate.snackbarBackgroundColor,
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
                    text = message,
                    style = TextStyle(
                        color = delegate.snackbarOnColor,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = Font.INTER,
                    )
                )
                Image(
                    modifier = Modifier.size(24.dp),
                    colorFilter = ColorFilter.tint(delegate.snackbarOnColor),
                    painter = painterResource(id = delegate.snackbarIcon),
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
            CustomSnackBar(
                R.drawable.baseline_check_24,
                delegate,
                "ethOS Component Library"
            ) }
    }
}




