package org.ethosmobile.components.library.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import org.ethosmobile.components.library.R


class Font {

    companion object {

        val INTER = FontFamily(
            Font(R.font.inter_light, FontWeight.Light),
            Font(R.font.inter_regular, FontWeight.Normal),
            Font(R.font.inter_medium, FontWeight.Medium),
            Font(R.font.inter_semibold, FontWeight.SemiBold),
            Font(R.font.inter_bold, FontWeight.Bold)
        )
    }
}