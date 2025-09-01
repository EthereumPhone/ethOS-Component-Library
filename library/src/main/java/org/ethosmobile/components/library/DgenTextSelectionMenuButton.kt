package org.ethosmobile.components.library

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.core.ui.util.label_fontSize
import org.ethosmobile.components.library.theme.SpaceMono

@Composable
fun DgenTextSelectionMenuButton(
    text: String,
    onClick: () -> Unit,
    primaryColor: Color
) {
    TextButton(
        onClick = onClick,
        modifier = Modifier.height(36.dp),
        contentPadding = PaddingValues(horizontal = 12.dp),
        colors = ButtonDefaults.textButtonColors(
            contentColor = primaryColor
        )
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontFamily = SpaceMono,
                fontSize = label_fontSize,
                fontWeight = FontWeight.SemiBold
            ),
            color = primaryColor
        )
    }
}