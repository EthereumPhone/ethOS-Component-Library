package org.ethosmobile.components.library.theme

import androidx.compose.ui.graphics.vector.ImageVector

class Haptic {

    companion object {
        val POSITIVE = HapticObject(
            timings = longArrayOf(
                25,25,25,25,25,200,25,25,25,25,25,25,25,25
            ),
            amplitudes = intArrayOf(
                0,20,40,60,80,100,80,60,50,40,30,20,10,0
            ),
            repeatIndex = -1
        )
        val NEUTRAL = HapticObject(
            timings = longArrayOf(
            25,25,25,25,25,200,25,25,25,25,25,25,25,25
            ),
            amplitudes = intArrayOf(
            0,20,40,60,80,100,80,60,50,40,30,20,10,0
            ),
            repeatIndex = -1
        )
        val NEGATIVE = HapticObject(
            timings = longArrayOf(
                100,25,25, 100,25,25
            ),
            amplitudes = intArrayOf(
                100,50,0, 100,50,0
            ),
            repeatIndex = -1
        )

    }
}

data class HapticObject(
    val timings: LongArray,
    val amplitudes: IntArray,
    val repeatIndex: Int,
)

//Use Haptic in combination w/ var vibrator =  LocalContext.current.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator