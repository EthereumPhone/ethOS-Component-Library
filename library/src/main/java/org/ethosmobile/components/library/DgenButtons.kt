package org.ethosmobile.components.library

import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.core.ui.util.label_fontSize
import com.core.ui.util.oceanAbyss
import com.core.ui.util.oceanCore
import com.core.ui.util.pulseOpacity
import org.ethosmobile.components.library.theme.SpaceMono

@Composable
fun DgenPrimaryButton(
    modifier: Modifier = Modifier,
    text: String = "Button",
    backgroundColor: Color = oceanAbyss,
    containerColor: Color = oceanCore,
    fontSize: TextUnit = label_fontSize,
    verticalPadding: Dp = 8.dp,
    horizontalPadding: Dp = 24.dp,
    onClick: () -> Unit = {},
    shape: RoundedCornerShape = RoundedCornerShape(size = 3.dp),
    enabled: Boolean = true,
){
    Surface(
        color = if(enabled) backgroundColor else backgroundColor.copy(pulseOpacity),
        shape = shape,
        modifier = modifier.pointerInput(Unit) {
            detectTapGestures {
                if(enabled){
                    onClick()
                }
            }
        },
    ) {
        Text(
            text = text.uppercase(),
            color = if(enabled) containerColor else containerColor.copy(pulseOpacity),
            style = TextStyle(
                fontFamily = SpaceMono,
                fontWeight = FontWeight.Bold,
                fontSize = fontSize,
                lineHeight = fontSize,
                letterSpacing = 0.sp,
                textDecoration = TextDecoration.None
            ),
            modifier = Modifier.padding(horizontal = horizontalPadding, vertical = verticalPadding)
        )
    }
}

@Composable
fun DgenSecondaryButton(
    modifier: Modifier = Modifier,
    text: String = "Button",
    containerColor: Color = oceanCore,
    fontSize: TextUnit = label_fontSize,
    verticalPadding: Dp = 8.dp,
    horizontalPadding: Dp = 24.dp,
    onClick: () -> Unit = {},
    shape: RoundedCornerShape = RoundedCornerShape(size = 3.dp),
    enabled: Boolean = true,
){
    Surface(
        color = Color.Transparent,
        shape = shape,
        modifier = modifier.pointerInput(Unit) {
            detectTapGestures {
                if(enabled){
                    onClick()
                }
            }
        },
    ) {
        Text(
            text = text.uppercase(),
            color = if(enabled) containerColor else containerColor.copy(pulseOpacity),
            style = TextStyle(
                fontFamily = SpaceMono,
                fontWeight = FontWeight.Bold,
                fontSize = fontSize,
                lineHeight = fontSize,
                letterSpacing = 0.sp,
                textDecoration = TextDecoration.None
            ),
            modifier = Modifier.padding(horizontal = horizontalPadding, vertical = verticalPadding)
        )
    }
}

@Composable
fun DgenBorderButton(
    modifier: Modifier = Modifier,
    text: String = "Button",
    containerColor: Color = oceanCore,
    fontSize: TextUnit = label_fontSize,
    verticalPadding: Dp = 8.dp,
    horizontalPadding: Dp = 24.dp,
    onClick: () -> Unit = {},
    shape: RoundedCornerShape = RoundedCornerShape(size = 3.dp),
    enabled: Boolean = true,
){
    Surface(
        color = Color.Transparent,
        shape = shape,
        modifier = modifier.border(2.dp,if(enabled) containerColor else containerColor.copy(pulseOpacity),shape).pointerInput(Unit) {
            detectTapGestures {
                if(enabled){
                    onClick()
                }
            }
        },
    ) {
        Text(
            text = text.uppercase(),
            color = if(enabled) containerColor else containerColor.copy(pulseOpacity),
            style = TextStyle(
                fontFamily = SpaceMono,
                fontWeight = FontWeight.Bold,
                fontSize = fontSize,
                lineHeight = fontSize,
                letterSpacing = 0.sp,
                textDecoration = TextDecoration.None
            ),
            modifier = Modifier.padding(horizontal = horizontalPadding, vertical = verticalPadding)
        )
    }
}