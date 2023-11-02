package org.ethosmobile.components.library.core

import android.os.VibrationEffect
import android.view.HapticFeedbackConstants
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue


class Haptics {

    val POSITIVE_HAPTIC: VibrationEffect = VibrationEffect.createWaveform(
        longArrayOf(
            25,25,25,25,25,250,25,25,25,25,25,25,25,25
        ),
        intArrayOf(
            0,20,40,60,80,100,80,60,50,40,30,20,10,0
        ),
        -1
    )

    //Use with View -> LocalView.current.performHapticFeedback(NEUTRAL_HAPTIC)
    val NEUTRAL_HAPTIC: Int = HapticFeedbackConstants.CONFIRM

    val NEGATIVE_HAPTIC: VibrationEffect = VibrationEffect.createWaveform(
        longArrayOf(
            100,25,25, 100,25,25
        ),
        intArrayOf(
            100,50,0, 100,50,0
        ),
        -1
    )
}