package org.ethosmobile.components.library.core

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import org.ethosmobile.components.library.theme.Colors
import org.ethosmobile.components.library.theme.Fonts


@Composable
fun ethOSSelectDialog(
    color: Color,
    title: String,
    firstOptionTitle: String,
    firstOptionIcon: ImageVector,
    onClickFirstOption: () -> Unit,
    secondOptionTitle: String,
    secondOptionIcon: ImageVector,
    onClickSecondOption: () -> Unit,
    setShowDialog: () -> Unit,

){

    Dialog(
        onDismissRequest = { setShowDialog() },
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = color,
            contentColor = Colors.WHITE,
            shadowElevation = 20.dp,
            border = BorderStroke(width = 1.dp, Colors.WHITE)
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(12.dp)

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
                                fontFamily = Fonts.INTER,
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center
                            )
                        )
                    }


                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Row (
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            IconButton(
                                onClick = onClickFirstOption,
                                modifier = Modifier.size(98.dp)
                            ) {
                                Column (
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Icon(
                                        imageVector = firstOptionIcon,
                                        contentDescription = firstOptionTitle,
                                        tint = Colors.WHITE,
                                        modifier = Modifier.size(64.dp)
                                    )
                                    Text(
                                        text = firstOptionTitle,
                                        style = TextStyle(
                                            fontSize = 14.sp,
                                            fontFamily = Fonts.INTER,
                                            fontWeight = FontWeight.SemiBold,
                                            textAlign = TextAlign.Center
                                        )
                                    )
                                }
                            }

                            IconButton(
                                onClick = onClickSecondOption,
                                modifier = Modifier.size(98.dp)
                            ) {
                                Column (
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Icon(
                                        imageVector = secondOptionIcon,
                                        contentDescription = secondOptionTitle,
                                        tint = Colors.WHITE,
                                        modifier = Modifier.size(64.dp)
                                    )
                                    Text(
                                        text = secondOptionTitle,
                                        style = TextStyle(
                                            fontSize = 14.sp,
                                            fontFamily = Fonts.INTER,
                                            fontWeight = FontWeight.Medium,
                                            textAlign = TextAlign.Center
                                        )
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
@Preview
@Composable
fun SelectDialogPreview(){
    ethOSSelectDialog(
        title = "Select",
        color = Color.Black,
        setShowDialog = {},
        firstOptionTitle = "String",
        firstOptionIcon = Icons.Outlined.CameraAlt,
        onClickFirstOption={},
        secondOptionTitle = "String",
        secondOptionIcon= Icons.Outlined.Image,
        onClickSecondOption={},
    )
}