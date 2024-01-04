package org.ethosmobile.components.library.lightnode

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton


import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.ethosmobile.components.library.core.ethOSButton
import org.ethosmobile.components.library.core.ethOSHeader
import org.ethosmobile.components.library.core.ethOSListItem
import org.ethosmobile.components.library.core.ethOSSwitch
import org.ethosmobile.components.library.theme.Colors
import org.ethosmobile.components.library.theme.Fonts

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ethOSSwitchBlock(
    modifier: Modifier= Modifier,
    options: Array<String>,
    onSelectedOption: (String) -> Unit,
    title: String = "",
    hasTitle: Boolean,
    icon: @Composable () -> Unit,
) {
    var expanded =  remember { mutableStateOf(false) }
    var selectedOption =  remember { mutableStateOf(options[0]) }


//    Column (
//        verticalArrangement = Arrangement.spacedBy(12.dp),
//        modifier = modifier.fillMaxWidth()
//    ) {
//        if (hasTitle) {
//            Row(
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Text(
//                    //modifier = modifier.padding(start = 16.dp),
//                    text = title,
//                    fontSize = 16.sp,
//                    color = Colors.GRAY,
//                    fontFamily = Fonts.INTER
//                )
//                Spacer(modifier = Modifier.width(8.dp))
//                icon()
//            }
//        }
//        Row(
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = modifier
//                .background(Color.Red)
//                .clickable {
//                    expanded.value = !expanded.value
//                }
//                .clip(RoundedCornerShape(16.dp))
//
//        ) {
//            Text(
//                text = selectedOption.value,
//                color = Colors.WHITE,
//                lineHeight = 109.sp,
//                style = TextStyle(
//                    fontSize = 18.sp,
//                    fontWeight = FontWeight.SemiBold,
//                    fontFamily = Fonts.INTER
//                ),
//                modifier = Modifier.padding(vertical = 16.dp)
//            )
//            Icon(
//                imageVector = if (expanded.value) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
//                contentDescription = null,
//                tint = Colors.WHITE,
//                modifier = Modifier.padding(end=12.dp)
//
//
//            )
//        }
//
//
//    }
//
//    ExposedDropdownMenuBox(
//        modifier = modifier,
//        expanded = expanded.value,
//        onExpandedChange = {
//            expanded.value = it
//        }
//    ) {
//        ExposedDropdownMenu(
//            expanded = expanded.value, onDismissRequest = { expanded.value = false },
//            modifier = modifier.fillMaxWidth()
//        ) {
//            options.forEach { option ->
//                DropdownMenuItem(
//                    modifier = modifier
//                        .fillMaxWidth()
//                        .clip(RoundedCornerShape(12.dp)),
//                    onClick = {
//                        selectedOption.value = option
//                        expanded.value = false
//                        onSelectedOption(option)
//                    },
//                    text = {
//                        Text(
//                            text = option,
//                            style = TextStyle(
//                                fontSize = 18.sp,
//                                fontWeight = FontWeight.SemiBold,
//                                fontFamily = Fonts.INTER,
//                                color = Colors.WHITE
//                            )
//                        )
//                    }
//                )
//            }
//        }
//
//
//
//
//
//
//    }

    if (expanded.value){
        Dialog(
            onDismissRequest = { expanded.value = false },
        ) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = Colors.BLACK,
                contentColor = Colors.WHITE,
                elevation = 20.dp,
                border = BorderStroke(width = 1.dp, Colors.WHITE)
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier
                            .padding(24.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(36.dp)

                    ) {

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = title,
                                style = TextStyle(
                                    fontSize = 24.sp,
                                    color = Colors.WHITE,
                                    fontFamily = Fonts.INTER,
                                    fontWeight = FontWeight.SemiBold,
                                    textAlign = TextAlign.Center
                                )
                            )
                        }

                        LazyColumn(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ){
                            options.forEach { option ->
                                item {
                                    Button(
                                        modifier = modifier.fillMaxWidth().height(56.dp),
                                        onClick = {
                                            selectedOption.value = option
                                            expanded.value = false
                                            onSelectedOption(option)
                                        },
                                        shape = CircleShape,
                                        colors =  ButtonDefaults.buttonColors(
                                            containerColor = if (selectedOption.value == option) Colors.WHITE else Colors.TRANSPARENT,
                                            contentColor = if (selectedOption.value == option) Colors.BLACK else Colors.WHITE,
                                            disabledContainerColor=Colors.TRANSPARENT ,
                                            disabledContentColor=Colors.GRAY
                                        ),
                                        elevation =  ButtonDefaults.buttonElevation(
                                            defaultElevation = if (selectedOption.value == option) 5.dp else 0.dp,
                                            pressedElevation = 0.dp,
                                            focusedElevation = 2.dp,
                                            hoveredElevation = 5.dp,
                                            disabledElevation = 0.dp
                                        ),

                                    ) {
                                        Text(
                                            text = option,
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            overflow = TextOverflow.Ellipsis,
                                            maxLines = 1,

                                        )
                                    }

                                }

                            }
                        }

                    }
                }
            }
        }
    }

    Column (
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        if (hasTitle) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    //modifier = modifier.padding(start = 16.dp),
                    text = title,
                    fontSize = 16.sp,
                    color = Colors.GRAY,
                    fontFamily = Fonts.INTER
                )
                Spacer(modifier = Modifier.width(8.dp))
                icon()
            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .clickable {
                    expanded.value = !expanded.value
                }
                .clip(RoundedCornerShape(12.dp))
        ) {
            Text(
                text = selectedOption.value,
                color = Colors.WHITE,
                lineHeight = 109.sp,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = Fonts.INTER
                ),
                modifier = Modifier.padding(vertical = 16.dp)
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null,
                tint = Colors.WHITE,
                modifier = Modifier
                    .padding(end = 12.dp)
            )
        }
    }
//
//
//        Column(
//            modifier = modifier
//                .fillMaxWidth()
//                .clip(RoundedCornerShape(12.dp))
//                .background(Colors.BLACK)
//        ) {
//
//
//            Row(
//                horizontalArrangement= Arrangement.SpaceBetween,
//                modifier = modifier
//                    .clickable {
//                        expanded.value = !expanded.value
//                    }
//            ){
//                Text(
//                    text = selectedOption.value,
//                    color = Colors.WHITE,
//                    lineHeight = 109.sp,
//                    style = TextStyle(
//                        fontSize = 18.sp,
//                        fontWeight = FontWeight.SemiBold,
//                        fontFamily = Fonts.INTER
//                    ),
//                    modifier = Modifier.padding(vertical = 16.dp)
//                )
//                Icon(
//                    imageVector = Icons.Default.ArrowDropDown,
//                    contentDescription = null,
//                    tint = Colors.WHITE,
//                    modifier = Modifier
//                        .padding(end = 12.dp)
//                )
//            }
//
//            Box(
//                modifier = Modifier
//                    .padding(horizontal = 12.dp)
//                    .requiredSizeIn(minWidth = 300.dp, maxWidth = 300.dp),
//                contentAlignment = Alignment.Center
//
//            ){
//                DropdownMenu(
//                    expanded = expanded.value,
//                    onDismissRequest = { expanded.value = false },
//                    modifier = modifier
//                        .requiredSizeIn(minWidth = 300.dp, maxWidth = 300.dp)
//
//                        .background(Colors.DARK_GRAY)
//                        .clip(RoundedCornerShape(18.dp))
//                    ,
//                ) {
//                    options.forEach { option ->
//                        DropdownMenuItem(
//                            modifier = modifier
//                                .clip(RoundedCornerShape(12.dp))
//                                .width(300.dp),
//                            onClick = {
//                                selectedOption.value = option
//                                expanded.value = false
//                                onSelectedOption(option)
//                            },
//                            text = {
//                                Text(
//                                    text = option,
//                                    style = TextStyle(
//                                        fontSize = 18.sp,
//                                        fontWeight = FontWeight.SemiBold,
//                                        fontFamily = Fonts.INTER,
//                                        color = Colors.WHITE
//                                    )
//                                )
//                            }
//                        )
//                    }
//                }
//            }
//
//            if(expanded.value){
//                Surface(
//
//                ) {
//                    Text(text = "HAllow", color = Color.White)
//                }
//            }
//
//
//        }
//    }

}





@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun PreviewSwitchBlock() {
    val options = arrayOf("Nimbus client", "Helios Client")

//    Column (
//
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier
//            .fillMaxSize()
//            .background(color = Colors.BLACK)
//            .padding(
//                vertical = 24.dp,
//            )
//
//
//    ){
//
//        Column {
//            Spacer(modifier = Modifier.height(16.dp))
//            ethOSSwitchBlock(
//                modifier = Modifier.width(360.dp),
//                options = options,
//                onSelectedOption = {},
//                title = "Light client",
//                hasTitle = true,
//                icon = {
//                    IconButton(
//                        modifier = Modifier.size(16.dp),
//                        onClick = {}//}
//                    ) {
//                        Icon(
//                            imageVector = Icons.Outlined.Info,
//                            contentDescription = "Information",
//                            tint = Color(0xFF9FA2A5),
//                            modifier = Modifier
//                                .clip(CircleShape)
//                            //.background(Color.Red)
//                        )
//                    }
//                }
//            )
//        }
//
//    }

    

    val test = remember {
        mutableStateOf(true)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Colors.BLACK)
            .padding(
                vertical = 24.dp,
            )

    ) {
        ethOSHeader(title="Light Node")

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 32.dp
                )
        ) {

            Spacer(
                modifier = Modifier
                    .height(height = 28.dp)
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                androidx.compose.material.Text(
                    text = "OFF/ON",
                    color = Colors.WHITE,
                    fontFamily = Fonts.INTER,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Box(modifier = Modifier.width(60.dp)) {
                    //ethOSBlock
                    ethOSSwitch(
                        switchON = test,
                        onCheckedChange = {
                            test.value = !test.value
                        }
                    )
                }
            }



            Spacer(
                modifier = Modifier
                    .height(height = 45.dp)
            )

            Column() {

                ethOSInfoBlock(
                    text = "Ethereum Mainnet",
                    title = "Network",
                    hasTitle = true,
                    icon = {
                        IconButton(
                            modifier = Modifier.size(16.dp),
                            onClick = { }//}
                        ) {
                            androidx.compose.material.Icon(
                                imageVector = Icons.Outlined.Info,
                                contentDescription = "Information",
                                tint = Colors.GRAY,
                                modifier = Modifier
                                    .clip(CircleShape)
                                //.background(Color.Red)
                            )
                        }
                    }
                )
                Spacer(
                    modifier = Modifier
                        .height(height = 16.dp)
                )


                ethOSSwitchBlock(
                    modifier = Modifier.fillMaxWidth(),
                    options = options,
                    onSelectedOption = {

                    },
                    title = "Light Client",
                    hasTitle = true,
                    icon = {
                        IconButton(
                            modifier = Modifier.size(16.dp),
                            onClick = {}//}
                        ) {
                            androidx.compose.material.Icon(
                                imageVector = Icons.Outlined.Info,
                                contentDescription = "Information",
                                tint = Color(0xFF9FA2A5),
                                modifier = Modifier
                                    .clip(CircleShape)
                                //.background(Color.Red)
                            )
                        }
                    }
                )

            }
            Spacer(
                modifier = Modifier
                    .height(height = 48.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                androidx.compose.material.Text(
                    text = "Latest Blocks",
                    color = Colors.WHITE,
                    lineHeight = 109.sp,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = Fonts.INTER
                    )
                )
                Spacer(
                    modifier = Modifier
                        .width(width = 8.dp)
                )




            }

            Spacer(
                modifier = Modifier
                    .height(height = 36.dp)
            )
            val scope = rememberCoroutineScope()
            val listState = rememberLazyListState()

            Column(

            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 18.dp)
                ) {

                    androidx.compose.material.Text(
                        text = "#",
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        color = Colors.GRAY,
                        modifier = Modifier.weight(.5f),
                        textAlign = TextAlign.Start,
                        fontFamily = Fonts.INTER

                    )
                    androidx.compose.material.Text(
                        text = "Tx",
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        color = Colors.GRAY,
                        modifier = Modifier.weight(.3f),
                        textAlign = TextAlign.Center,
                        fontFamily = Fonts.INTER

                    )
                    androidx.compose.material.Text(
                        text = "Gas",
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        color = Colors.GRAY,
                        modifier = Modifier.weight(.3f),
                        textAlign = TextAlign.Center,
                        fontFamily = Fonts.INTER
                    )
                    Box(
                        modifier = Modifier.weight(.1f),
                        contentAlignment = Alignment.CenterEnd,
                    ) {
                        androidx.compose.material.Icon(
                            imageVector = Icons.Filled.KeyboardArrowRight,
                            contentDescription = "Block Details",
                            tint = Colors.TRANSPARENT
                        )
                    }

                }


                        //Opacity Animation
                        val transition = rememberInfiniteTransition()
                        val fadingAnimation by transition.animateFloat(
                            initialValue = 1.0f,
                            targetValue = 1f,
                            animationSpec = infiniteRepeatable(
                                animation = keyframes {
                                    durationMillis = 2000
                                    1.0f at 0 with LinearEasing
                                    0f at 1000 with LinearEasing
                                    1.0f at 2000 with LinearEasing
                                }
                            )
                        )

                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            androidx.compose.material.Text(
                                modifier = Modifier.alpha(fadingAnimation),
                                text = "Finding Blocks...",
                                fontFamily = Fonts.INTER,
                                fontWeight = FontWeight.SemiBold,
                                color = Colors.GRAY,
                                fontSize = 16.sp

                            )
                        }

            }


        }

    }

}
