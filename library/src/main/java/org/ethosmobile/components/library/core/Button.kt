package org.ethosmobile.components.library.core

import androidx.compose.foundation.background
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.ethosmobile.components.library.theme.Colors
import org.ethosmobile.components.library.theme.Font


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
            Text(text=text, color= Colors.BLACK, fontWeight = FontWeight.SemiBold, fontSize = 18.sp)

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


@Preview
@Composable
private fun PreviewButton() {
    ethOSButton(
        onClick = { /*TODO*/ },
        enabled = true,
        text = "Send"
    )
}