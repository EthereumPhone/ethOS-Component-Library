package org.ethosmobile.components.library.models

import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.vector.ImageVector

data class OnboardingItem(
    val imageVector: ImageVector,
    val title: String,
    val subtitle: String,
)

data class OnboardingObject(
    val imageVector: Int,
    val title: String,
    val items: List<OnboardingItem>
)
