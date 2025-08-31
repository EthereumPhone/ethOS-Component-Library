package org.ethosmobile.components.library.theme

import android.content.Context
import android.provider.Settings
import android.util.Log
import androidx.annotation.ColorInt
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.core.ui.util.gunMetalCore
import com.core.ui.util.gunMetalForge
import com.core.ui.util.lazerBurn
import com.core.ui.util.lazerCore
import com.core.ui.util.oceanAbyss
import com.core.ui.util.oceanCore
import com.core.ui.util.orcheAsh
import com.core.ui.util.orcheCore
import com.core.ui.util.terminalCore
import com.core.ui.util.terminalHack

/**
 * Manages two dynamic colors (Primary & Secondary),
 * which can be read from the system accent colors when the app starts.
 * The colors are held as `mutableStateOf`, so that any change
 * automatically triggers recomposition in @Composable callers.
 */
object SystemColorManager {

    private val DEFAULT_PRIMARY = lazerCore
    private val DEFAULT_SECONDARY = lazerBurn
    private val DEFAULT_TERITARY = Color(0xFF820303)

    /** Current primary color */
    var primaryColor by mutableStateOf(DEFAULT_PRIMARY)
        private set

    @get:ColorInt
    val primaryColorInt: Int
        get() = primaryColor.toArgb()

    /** Current secondary color */
    var secondaryColor by mutableStateOf(DEFAULT_SECONDARY)
        private set

    @get:ColorInt
    val secondaryColorInt: Int
        get() = secondaryColor.toArgb()

    /** Current teritary color */
    var teritaryColor by mutableStateOf(DEFAULT_TERITARY)
        private set

    @get:ColorInt
    val teritaryColorInt: Int
        get() = teritaryColor.toArgb()

    /**
     * Reads the current system accent color and updates the fields.
     * Should be executed, for example, in the `LaunchedEffect` of a screen.
     */
    fun refresh(context: Context) {
        // Android stores the accent color in Secure Settings from Android 12 onwards.
        // The fallback is an intense red if no entry is present.
        val accentInt = Settings.Secure.getInt(
            context.contentResolver,
            "systemui_accent_color",
            DEFAULT_PRIMARY.toArgb()
        )

        val accentColor = Color(accentInt)
        Log.d("SystemColorManager","accentInt: $accentInt, accentColor: $accentColor")

        //Decide the colorway
        // Map a few well-known accent colours to our predefined colourways, otherwise
        // fall back to using the accent colour exactly as provided by the system. This
        // guarantees we always match the user’s chosen theme—even on devices whose
        // accent values we haven’t explicitly whitelisted yet.
        when (accentInt) {
            // TERMINAL (green)
            -13510400 -> {
                primaryColor = terminalCore
                secondaryColor = terminalHack
                teritaryColor = Color(0xFF186103)
            }
            // LAZER (red)
            -131072,  // some devices report this value
            -65536   // pure #FF0000
            -> {
                primaryColor = lazerCore
                secondaryColor = lazerBurn
                teritaryColor = Color(0xFF820303)
            }
            // OCEAN (cyan/blue)
            -16718593 -> {
                primaryColor = oceanCore
                secondaryColor = oceanAbyss
                teritaryColor = Color(0xFF037582)
            }
            // ORCHE (orange)
            -1012183 -> {
                primaryColor = orcheCore
                secondaryColor = orcheAsh
                teritaryColor = Color(0xFF7B4A17)
            }
            // GUNMETAL (grey)
            -3618616 -> {
                primaryColor = gunMetalCore
                secondaryColor = gunMetalForge
                teritaryColor = Color(0xFF676767)
            }
            else -> {
                // Unknown accent – just use it directly for everything to keep things simple
                primaryColor = accentColor
                secondaryColor = accentColor
                teritaryColor = accentColor
            }
        }

        //-13510400 - Green
        // -131072 - Red
        // -16718593 - blue
        // -1012183 - orange
        // -3618616 - Gray
    }

    /**
     * Allows to manually override the colors (e.g. for testing).
     */
    fun setColors(primary: Color, secondary: Color) {
        primaryColor = primary
        secondaryColor = secondary
    }
} 