package com.example.ethoscomponents.util

import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import com.example.ethoscomponents.components.NumPadDefaults

@Composable
fun IconAsText(
    icon: ImageVector,
    modifier: Modifier = Modifier,
    contentColor: Color = NumPadDefaults.contentColor,
) {
    val myId = "inlineContent"
    val text = buildAnnotatedString {
        append("")
        appendInlineContent(myId, "[icon]")
    }
    val inlineContent = mapOf(
        Pair(
            myId,
            InlineTextContent(
                Placeholder(
                    width = NumPadDefaults.fontSize,
                    height = NumPadDefaults.fontSize,
                    placeholderVerticalAlign = PlaceholderVerticalAlign.AboveBaseline
                )
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = contentColor,
                )
            }
        )
    )
    Text(text = text,
        inlineContent = inlineContent,
        modifier = modifier
    )
}