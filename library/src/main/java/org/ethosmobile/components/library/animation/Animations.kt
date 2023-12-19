package org.ethosmobile.components.library.animation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.runtime.getValue

//Opacity Animation

//val transition = rememberInfiniteTransition("")
//val fadingAnimation by transition.animateFloat(
//    initialValue = 1.0f,
//    targetValue = 1f,
//    animationSpec = infiniteRepeatable(
//        animation = keyframes {
//            durationMillis = 2000
//            1.0f at  0 with LinearEasing
//            0f at  1000 with LinearEasing
//            1.0f at  2000 with LinearEasing
//        }
//    ), label = ""
//)

val infiniteFadingAnimationSpec = infiniteRepeatable(
        animation = keyframes {
            durationMillis = 2000
            1.0f at  0 with LinearEasing
            0f at  1000 with LinearEasing
            1.0f at  2000 with LinearEasing
        }
)
