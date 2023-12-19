package org.ethosmobile.components.library.core

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.ethosmobile.components.library.theme.Colors

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ethOSSwitch(
    //checked: Boolean,
    //onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    switchON: MutableState<Boolean>,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: SwitchColors = SwitchDefaults.colors(),
    onCheckedChange: (Boolean) -> Unit
) {

    var horizontalBias = if (switchON.value) 1f else -1f
    val alignment by animateHorizontalAlignmentAsState(horizontalBias)
    BoxWithConstraints(
        modifier = Modifier
            .clip(RoundedCornerShape(50.dp))
            .background(if (horizontalBias == -1f) Colors.GRAY else Color(0xFF94DE7E))
            .padding(horizontal = 2.dp, vertical = 2.dp)
            .clickable {
                horizontalBias *= -1f
                switchON.value = horizontalBias == 1f
                onCheckedChange(switchON.value)
            }

    ) {
        Box(
            modifier = Modifier
                .size(25.dp)
                .clip(CircleShape)
                .background(Colors.WHITE)
                .align(alignment)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(text = "".uppercase(), modifier = Modifier.padding(start=8.dp), color = if (horizontalBias == 1f) Colors.WHITE else Colors.TRANSPARENT, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "".uppercase(), modifier = Modifier.padding(end=8.dp), color = if (horizontalBias == -1f) Colors.WHITE else Colors.TRANSPARENT, fontWeight = FontWeight.SemiBold)
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
private fun animateHorizontalAlignmentAsState(
    targetBiasValue: Float
): State<BiasAlignment> {
    val bias by animateFloatAsState(targetBiasValue)
    return derivedStateOf { BiasAlignment(horizontalBias = bias, verticalBias = 0f) }
}

@Preview
@Composable
fun TestPeview() {
    val isOnlineVar = remember { mutableStateOf(false) }

    ethOSSwitch(switchON = isOnlineVar) {}
}


/*@Preview(showBackground = true, widthDp = 390, heightDp = 800)
@Composable
fun PreviewMainScreen() {
    ethOSTheme() {
        Switch()
    }

}*/