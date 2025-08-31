package org.ethosmobile.components.library

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.core.ui.util.dgenOcean
import com.core.ui.util.dgenWhite
import org.ethosmobile.components.library.theme.SpaceMono

@Composable
fun PickerButton(
    text: String,
    isSelected: Boolean,
    onSelected: () -> Unit,
    modifier: Modifier = Modifier,
    selectedColor: Color = dgenOcean,
    unselectedColor: Color = Color.Transparent,
    selectedTextColor: Color = dgenWhite,
    unselectedTextColor: Color = dgenOcean,
    borderColor: Color = dgenOcean
) {
    Box(
        modifier = modifier
            .width(64.dp)
            .aspectRatio(16f / 9f)
            .clip(RoundedCornerShape(4.dp))
            .background(if (isSelected) selectedColor else unselectedColor)
            .border(1.dp, borderColor, RoundedCornerShape(4.dp))
            .clickable { onSelected() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontFamily = SpaceMono,
                color = if (isSelected) selectedTextColor else unselectedTextColor,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PercentageButtonPreview() {
    Box(modifier = Modifier.background(Color.White)) {
        PickerButton(
            text = "25%",
            isSelected = true,
            onSelected = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PercentageButtonUnselectedPreview() {
    Box(modifier = Modifier.background(Color.White)) {
        PickerButton(
            text = "25%",
            isSelected = false,
            onSelected = {}
        )
    }
}

