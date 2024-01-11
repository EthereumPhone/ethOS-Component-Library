package org.ethosmobile.components.library.core

import androidx.compose.foundation.background
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.ethosmobile.components.library.R
import org.ethosmobile.components.library.theme.Color
import org.ethosmobile.components.library.theme.Font


@Composable
fun ethOSButton(
    text: String,
    IconDrawable: Int = R.drawable.baseline_arrow_downward_24,
    enabled: Boolean,
    primary: Boolean = true,
    onClick: (() -> Unit)? = null,
    interactionSource: MutableInteractionSource =
        remember { MutableInteractionSource() }
) {

    //val interactionSource = remember { MutableInteractionSource() }
    //val isPressed by interactionSource.collectIsPressedAsState() //if pressed
    //val isHovering by interactionSource.collectIsFocusedAsState() //if hovered

    Button(
        interactionSource = interactionSource,
        onClick = {
            if (onClick != null) {
                onClick()
            }
        },
        shape = RoundedCornerShape(50.dp),
        enabled = enabled,
        modifier = Modifier
            .fillMaxWidth()
            //.hoverable(interactionSource = interactionSource, enabled = true)
            .indication(interactionSource, rememberRipple(bounded = true, color = Color.BLACK))

        ,
        contentPadding= PaddingValues(horizontal = 8.dp, vertical = 8.dp),
        colors = ButtonDefaults.buttonColors(
            disabledBackgroundColor = Color.GRAY,
            backgroundColor = if(primary) Color.WHITE else Color.BLUE,//if(primary) (if(isPressed) positive else warning ) else blue,
            contentColor = Color.BLUE
        )
    ) {

        Row(
            horizontalArrangement =  Arrangement.SpaceBetween,
            verticalAlignment =  Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ){


            var iconcolor = Color.BLUE
            if(enabled) {
                if(primary){
                    iconcolor = Color.BLUE
                }else{
                    //secondary
                    iconcolor = Color.WHITE
                }
            }else {
                iconcolor = Color.BLUE
            }
            Box (
                modifier = Modifier
                    .clip(CircleShape)
                    .background(iconcolor)
                    .size(36.dp)
                ,
                contentAlignment= Alignment.Center
            ) {
                Icon(
                    //imageVector = ButtonIcon,//Icons.Default.ArrowUpward,

                    painter = painterResource(id = IconDrawable),
                    contentDescription = text,
                    modifier = Modifier
                        .size(24.dp),
                    tint = if(enabled) ( if(primary) Color.WHITE else Color.BLUE) else Color.GRAY
                )
            }
            Text(text=text, color= if(enabled) ( if(primary) Color.BLUE else Color.WHITE) else Color.BLUE, fontWeight = FontWeight.SemiBold, fontSize = 16.sp, fontFamily = Font.PRIMARY)
            Box (
                modifier = Modifier

                    .clip(CircleShape)
                    .background(Color.TRANSPARENT)
                    .size(36.dp)
                ,
                contentAlignment= Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = text,
                    modifier = Modifier
                        .size(20.dp),
                    tint = Color.TRANSPARENT
                )
            }



        }

    }

}

/*@Composable
@Preview
fun PreviewButton() */