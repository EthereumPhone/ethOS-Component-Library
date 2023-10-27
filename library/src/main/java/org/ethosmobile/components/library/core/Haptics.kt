package org.ethosmobile.components.library.core

import android.os.VibrationEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class Haptics {

    val POSITIVE_HAPTIC: VibrationEffect = VibrationEffect.createWaveform(
        longArrayOf(
            25,25,25,25,25,200,25,25,25,25,25,25,25,25
        ),
        intArrayOf(
            0,20,40,60,80,100,80,60,50,40,30,20,10,0
        ),
        -1
    )

    val NEUTRAL_HAPTIC: VibrationEffect = VibrationEffect.startComposition().addPrimitive(
        VibrationEffect.Composition.PRIMITIVE_SLOW_RISE, 1F,).compose()


    val NEGATIVE_HAPTIC: VibrationEffect = VibrationEffect.startComposition()
        .addPrimitive(
            VibrationEffect.Composition.PRIMITIVE_QUICK_FALL,//PRIMITIVE_CLICK,
            0.5F,
            50
        ).addPrimitive(
            VibrationEffect.Composition.PRIMITIVE_QUICK_FALL,//PRIMITIVE_CLICK,
            0.7F,
            100
        ).compose()
}