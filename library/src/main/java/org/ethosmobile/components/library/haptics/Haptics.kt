package org.ethosmobile.components.library.haptics

import android.os.VibrationEffect
import android.view.HapticFeedbackConstants

class EthOSHaptics {

    //Use like this: vibrator.vibrator(Haptics().POSITIVE_HAPTIC)
    val POSITIVE_HAPTIC: VibrationEffect = VibrationEffect.createWaveform(
        longArrayOf(
            25,25,25,25,25,250,25,25,25,25,25,25,25,25
        ),
        intArrayOf(
            0,20,40,60,80,100,80,60,50,40,30,20,10,0
        ),
        -1
    )

    //Use like this: vibrator.vibrator(Haptics().NEGATIVE_HAPTIC)
    val NEGATIVE_HAPTIC: VibrationEffect = VibrationEffect.createWaveform(
        longArrayOf(
            100,25,25, 100,25,25
        ),
        intArrayOf(
            100,50,0, 100,50,0
        ),
        -1
    )

    //Use with View -> LocalView.current.performHapticFeedback(Haptics().NEUTRAL_HAPTIC) or
    // view.performHapticFeedback(Haptics().NEUTRAL_HAPTIC)
    val NEUTRAL_HAPTIC: Int = HapticFeedbackConstants.CONFIRM
}