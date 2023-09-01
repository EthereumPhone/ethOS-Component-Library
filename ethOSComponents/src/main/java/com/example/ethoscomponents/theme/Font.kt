package com.example.ethoscomponents.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.ethoscomponents.R


class Font {

    companion object {

        val PRIMARY = FontFamily(
            Font(R.font.inter_light, FontWeight.Light),
            Font(R.font.inter_regular, FontWeight.Normal),
            Font(R.font.inter_medium, FontWeight.Medium),
            Font(R.font.inter_semibold, FontWeight.SemiBold),
            Font(R.font.inter_bold, FontWeight.Bold)
        )
    }
}