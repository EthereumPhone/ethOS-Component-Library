package org.ethosmobile.components.library

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.core.ui.util.dgenBlack
import com.core.ui.util.neonOpacity
import org.ethosmobile.components.library.theme.PitagonsSans

@Composable
fun ConfirmationOverlay(
    visible: Boolean = true,
    description: String,
    extraDescription: String? = null,
    primaryColor: Color,
    secondaryColor: Color,
    onDelete: () -> Unit,
    onCancel: () -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {

        // Layer 1: full-screen clickable background. Tapping it will cancel.
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(dgenBlack)
                .pointerInput(Unit) {
                    detectTapGestures { onCancel() }
                }
        )

        // Layer 2: centred content column that intercepts taps (so taps don't reach background)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) { /* Intercept to prevent dismissal */ },
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(32.dp),
                modifier = Modifier
                    .padding(horizontal = 24.dp)
            ) {
                // Message Section
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = description,
                        style = TextStyle(
                            textAlign = TextAlign.Center,
                            fontFamily = PitagonsSans,
                            color = primaryColor,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 22.sp,
                            lineHeight = 28.sp,
                            letterSpacing = 0.sp,
                            textDecoration = TextDecoration.None,
                        ),
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .fillMaxWidth()
                    )

                    extraDescription?.let {
                        Text(
                            text = it,
                            style = TextStyle(
                                textAlign = TextAlign.Center,
                                fontFamily = PitagonsSans,
                                color = primaryColor.copy(alpha = neonOpacity),
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.sp,
                                lineHeight = 20.sp,
                                letterSpacing = 0.sp,
                                textDecoration = TextDecoration.None,
                            ),
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .fillMaxWidth()
                        )
                    }
                }

                // Action buttons
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    DgenPrimaryButton(
                        text = "CANCEL",
                        backgroundColor = primaryColor,
                        containerColor =  secondaryColor,
                        onClick = onCancel
                    )

                    DgenSecondaryButton(
                        text = "DELETE",
                        containerColor =  primaryColor,
                        onClick = onDelete
                    )

                }
            }
        }
    }
} 