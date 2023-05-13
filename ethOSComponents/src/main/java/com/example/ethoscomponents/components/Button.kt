package com.example.componentlibrary.ui.components

import androidx.compose.foundation.Indication
import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.componentlibrary.ui.theme.gray
import com.example.componentlibrary.ui.theme.white
import com.example.componentlibrary.ui.theme.blue
import com.example.componentlibrary.ui.theme.ethOSTheme
import com.example.componentlibrary.ui.theme.positive
import com.example.componentlibrary.ui.theme.warning
import com.example.ethoscomponents.R


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


    val Inter = FontFamily(
        Font(R.font.inter_light, FontWeight.Light),
        Font(R.font.inter_regular, FontWeight.Normal),
        Font(R.font.inter_medium, FontWeight.Medium),
        Font(R.font.inter_semibold, FontWeight.SemiBold),
        Font(R.font.inter_bold, FontWeight.Bold)
    )

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
            .indication(interactionSource, rememberRipple(bounded = true, color = Color.Black))

        ,
        contentPadding= PaddingValues(horizontal = 8.dp, vertical = 8.dp),
        colors = ButtonDefaults.buttonColors(
            disabledBackgroundColor = gray,
            backgroundColor = if(primary) white else blue,//if(primary) (if(isPressed) positive else warning ) else blue,
            contentColor = blue
        )
    ) {

        Row(
            horizontalArrangement =  Arrangement.SpaceBetween,
            verticalAlignment =  Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ){


            var iconcolor = blue
            if(enabled) {
                if(primary){
                    iconcolor = blue
                }else{
                    //secondary
                    iconcolor = white
                }
            }else {
                iconcolor = blue
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
                    tint = if(enabled) ( if(primary) white else blue) else gray
                )
            }
            Text(text=text, color= if(enabled) ( if(primary) blue else white) else blue, fontWeight = FontWeight.SemiBold, fontSize = 16.sp, fontFamily = Inter)
            Box (
                modifier = Modifier

                    .clip(CircleShape)
                    .background(Color.Transparent)
                    .size(36.dp)
                ,
                contentAlignment= Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = text,
                    modifier = Modifier
                        .size(20.dp),
                    tint = Color.Transparent
                )
            }



        }

    }

}

/*@Composable
@Preview
fun PreviewButton() */