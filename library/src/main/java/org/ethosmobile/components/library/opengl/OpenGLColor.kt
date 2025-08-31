package org.ethosmobile.components.library.opengl

import android.annotation.SuppressLint

data class OpenGLColor(
    val r: Float = 0.0f,
    val g: Float = 0.0f,
    val b: Float = 0.0f,
    val a: Float = 1.0f,
)

@SuppressLint("DefaultLocale")
fun normalizeColor(color: Float): Float{
    return (color/255f)
}

fun hexToRGBA(hex: String): FloatArray {
    val color = hex.removePrefix("#").toInt(16)
    val r = ((color shr 16) and 0xFF) / 255.0f
    val g = ((color shr 8) and 0xFF) / 255.0f
    val b = (color and 0xFF) / 255.0f
    return floatArrayOf(r, g, b, 1.0f) // Default full opacity
}