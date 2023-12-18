package org.ethosmobile.components.library.core

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material.swipeable
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
//import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.ethosmobile.components.library.theme.Colors
import org.ethosmobile.components.library.theme.Font
import org.ethosmobile.components.library.utils.SwipeButtonDefaults
import kotlin.math.roundToInt


@Composable
fun ethOSButton(
    modifier: Modifier = Modifier,
    text: String, enabled: Boolean,
    onClick: () -> Unit,
    interactionSource: MutableInteractionSource =
        remember { MutableInteractionSource() }
) {
    Button(
        interactionSource = interactionSource,
        onClick = onClick,
        shape = RoundedCornerShape(50.dp),
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(54.dp)
            .indication(interactionSource, rememberRipple(bounded = true, color = Colors.BLACK))
        ,
        contentPadding= PaddingValues(horizontal = 8.dp, vertical = 8.dp),
        colors = ButtonDefaults.buttonColors(
            disabledBackgroundColor = Colors.GRAY,
            disabledContentColor = Colors.WHITE ,//if(primary) (if(isPressed) positive else warning ) else blue,
            contentColor = Colors.BLACK,
            backgroundColor = Colors.WHITE
        )
    ) {

        Row(
            horizontalArrangement =  Arrangement.Center,
            verticalAlignment =  Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ){
            Text(
                text=text,
                color= Colors.BLACK,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                fontFamily = Font.INTER
            )

        }

    }

}

@Composable
fun ethOSIconButton(
    onClick: () -> Unit,
    icon: ImageVector,
    contentDescription: String? = null,
) = Column(
    horizontalAlignment = Alignment.CenterHorizontally
) {
    IconButton(
        onClick = onClick,
        colors = IconButtonDefaults.filledIconButtonColors(containerColor = Color.Transparent),
        modifier = Modifier.size(48.dp)
    ) {
        Icon(
            imageVector = icon,
            modifier = Modifier.size(32.dp),
            contentDescription = contentDescription,
            tint = Color.White//(0xFF24303D)//background
        )
    }
    if (contentDescription != null) {
        Text(
            text = contentDescription,
            color = Color.White,
            modifier = Modifier.padding(top = 4.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}


@Composable
fun ethOSTagButton(
    text: String,
    onClick: () -> Unit
){
    Button(
        onClick = onClick,
        contentPadding = PaddingValues(start = 12.dp, end = 6.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Colors.DARK_GRAY,
            contentColor = Colors.WHITE
        ),
        shape = CircleShape,
        content = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
                Icon(
                    imageVector = Icons.Rounded.ChevronRight,
                    contentDescription = "",
                    tint = Colors.WHITE,
                    modifier = Modifier.size(24.dp).rotate(90f)
                )
            }
        }
    )


}

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterialApi::class)
@Composable
fun SwipeButton(
    text: String,
    icon: ImageVector,
    completeIcon: ImageVector,
    modifier: Modifier = Modifier,
    //enabled: Boolean,
//    result: String,
//    swipeableState: SwipeableState<Int>,
//    swipeComplete: Boolean,
//    setSwipeComplete: (Boolean) -> Unit,
    onSwipe: () -> Unit
) {
    val swipeableState = rememberSwipeableState(initialValue = 0)
    val (swipeComplete, setSwipeComplete) = remember {
        mutableStateOf(false)
    }
    val alpha: Float by animateFloatAsState(
        if(swipeableState.offset.value > 10f) (1 - swipeableState.progress.fraction) else 1f
    )

    LaunchedEffect(key1 = swipeableState.currentValue) {
        if(swipeableState.currentValue == 1) {
            setSwipeComplete(true)
            onSwipe()
        }
    }
    BoxWithConstraints(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(CircleShape)
            .background(Color.White)
            .padding(if (swipeComplete) PaddingValues(SwipeButtonDefaults.verticalPadding) else SwipeButtonDefaults.paddingValues)
            .animateContentSize()
            .then(
                if (swipeComplete) {
                    Modifier.size(SwipeButtonDefaults.iconContainer)
                } else {
                    Modifier.fillMaxWidth()
                }
            )
    ) {
        val iconSize = SwipeButtonDefaults.iconContainer
        val boxWidth = this.maxWidth
        val paddingWidth = SwipeButtonDefaults.horizontalPadding
        val swipeWidth = with(LocalDensity.current) {
            boxWidth.toPx() - iconSize.toPx() - paddingWidth.toPx()
        }
        val horizontalAnchors = mapOf(0f to 0, swipeWidth to 1)

        SwipeIndicator(
            icon = icon,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .offset {
                    IntOffset(swipeableState.offset.value.roundToInt(), 0)
                }
                .alpha(if (swipeComplete) 0f else 1f)
                .swipeable(
                    state = swipeableState,
                    anchors = horizontalAnchors,
                    thresholds = { _, _ ->
                        FractionalThreshold(0.5F)
                    },
                    orientation = Orientation.Horizontal
                )
        )
        androidx.compose.material3.Text(
            text = text,
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            modifier = Modifier
                .align(Alignment.Center)
                .alpha(if (swipeComplete) 0f else alpha)
        )
        AnimatedVisibility(
            visible = swipeComplete,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            androidx.compose.material3.Icon(
                completeIcon,
                contentDescription = "",
                modifier = Modifier.padding(2.dp),
                tint = Color(0xFF24303D)
            )
        }
    }
}


@Composable
fun SwipeIndicator(
    modifier: Modifier = Modifier,
    icon: ImageVector
) {
    Surface(
        shape = CircleShape,
        color = Color.Black,
        modifier = modifier
            .size(36.dp)
    ) {
        androidx.compose.material3.Icon(
            icon,
            contentDescription = "",
            modifier = Modifier.padding(2.dp),
            tint = Color.White
        )
    }
}




@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
private fun PreviewSwipeButton() {
    Surface(
        color = Color.Black
    ) {
        val coroutineScope = rememberCoroutineScope()
        val (isComplete, setIsComplete) = remember {
            mutableStateOf(false)
        }
        Column(
            Modifier
                .fillMaxWidth()
                .padding(10.dp)
            ,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SwipeButton(
                modifier = Modifier,
                icon = Icons.Filled.ArrowForward,
                completeIcon = Icons.Filled.Check,
                text = "Swipe",
                //enabled = true,
            ) { coroutineScope.launch {
                setIsComplete(true)
            }
            }
            //Text(text=isComplete.toString(), color = Color.Red)
        }
    }
}


@Preview
@Composable
private fun PreviewButton() {
    ethOSButton(
        onClick = { /*TODO*/ },
        enabled = true,
        text = "Send"
    )
}