package org.ethosmobile.components.library

// New composable implementing token image field with animated background similar to SimpleDgenTextfield

import android.net.Uri
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import coil.compose.AsyncImage
import com.contacts.dgenlibrary.theme.ghostOpacity
import com.contacts.dgenlibrary.theme.neonOpacity
import com.contacts.dgenlibrary.theme.pulseOpacity
import com.core.ui.DgenTheme
import com.core.ui.util.ghostOpacity
import com.core.ui.util.neonOpacity

import com.freedomfactory.tokenlauncher.ui.theme.DgenTheme
import com.freedomfactory.tokenlauncher.ui.theme.label_fontSize

import kotlinx.coroutines.delay

/**
 * A composable that shows a token image picker box with a label and animated background
 * matching the style of [com.tokenlauncher.dgenlibrary.SimpleDgenTextfield]. The background
 * fades in when the box is tapped and fades out automatically after a short delay.
 *
 * @param imageUri Currently selected image URI, or `null` if none selected.

 * @param onPickImage Lambda invoked when user taps the box to select an image.
 * @param modifier Modifier for this composable.
 * @param label Text displayed above the box. Defaults to "TOKEN IMAGE".
 */
@Composable
fun TokenImageField(
    imageUri: Uri?,
    primaryColor: Color,
    secondaryColor: Color,
    onPickImage: () -> Unit,
    modifier: Modifier = Modifier,
    label: String = "TOKEN IMAGE",
) {
    // Track pressed state to animate background
    var isPressed by remember { mutableStateOf(false) }

    // Automatically reset pressed state after short delay
    LaunchedEffect(isPressed) {
        if (isPressed) {
            delay(300) // matches the tween duration below
            isPressed = false
        }
    }

    val animatedBgAlpha by animateFloatAsState(
        targetValue = if (isPressed) ghostOpacity else 0f,
        animationSpec = tween(durationMillis = 300),
        label = "bgAlpha"
    )


    Column(
        modifier = modifier
            .clip(RoundedCornerShape(0.dp))
            .drawBehind {
                // Draw animated background the size of the composable
                drawRect(
                    color = secondaryColor,
                    size = size,
                    topLeft = Offset.Zero,
                    alpha = animatedBgAlpha
                )
            }
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        // Label (using the same typography as other labels)
        Text(
            text = label,
            style = DgenTheme.typography.label,
            color = primaryColor
        )

        // Image area – 100dp square box, left-aligned
        Box(
            modifier = Modifier
                .size(112.dp) // Fixed 100dp square
                .background(secondaryColor, RoundedCornerShape(8.dp))
                .clickable {
                    isPressed = true
                    onPickImage()
                },
            contentAlignment = Alignment.Center
        )
        {
            if (imageUri != null) {
                AsyncImage(
                    model = imageUri,
                    contentDescription = "Token Image",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = androidx.compose.ui.layout.ContentScale.Crop
                )
            }
            else {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = "Select Image",
                        modifier = Modifier.size(28.dp),
                        tint = primaryColor.copy(neonOpacity)
                    )
                    Text(
                        text = "UPLOAD",
                        style = DgenTheme.typography.label,
                        fontSize = 16.sp,
                        color = primaryColor.copy(neonOpacity)
                    )

                }

            }


        }
    }
}
