package org.ethosmobile.components.library

import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.nativeKeyCode
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalTextToolbar
import androidx.compose.ui.platform.TextToolbar
import androidx.compose.ui.platform.TextToolbarStatus
import androidx.compose.ui.geometry.Rect
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.runtime.Stable
import androidx.compose.runtime.DisposableEffect as ComposeDisposableEffect
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.compose.foundation.shape.RoundedCornerShape as CardRoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text as MaterialText
import androidx.compose.material3.TextButton
import androidx.compose.material3.ButtonDefaults
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row as MaterialRow
import androidx.compose.foundation.layout.height
import androidx.compose.ui.draw.shadow
import androidx.compose.foundation.background
import com.core.ui.util.body2_fontSize
import com.core.ui.util.dgenOcean
import com.core.ui.util.dgenTurqoise
import com.core.ui.util.dgenWhite
import com.core.ui.util.ghostOpacity
import com.core.ui.util.label_fontSize
import org.ethosmobile.components.library.theme.PitagonsSans
import org.ethosmobile.components.library.theme.SpaceMono
import org.ethosmobile.components.library.theme.SystemColorManager

@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
@Composable
fun SimpleDgenTextfield(
    value: TextFieldValue = TextFieldValue(""),
    onValueChange: (TextFieldValue) -> Unit,
    keyboardtype: KeyboardType = KeyboardType.Number,
    autoCorrectEnabled: Boolean = true,
    textfieldFocusManager: FocusManager? = null,
    onEditDone: () -> Unit,
    onDoubleTap: () -> Unit = {},
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    singleLine: Boolean = true,
    minLines: Int = 1,
    maxLines: Int = Int.MAX_VALUE,
    maxLength: Int = 100,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = RoundedCornerShape(8.dp),
    backgroundColor: Color = dgenOcean,
    cursorColor: Color = dgenWhite,
    cursorWidth: Dp = 18.dp,
    cursorHeight: Dp = 32.dp,
    activeColor: Color = dgenOcean,
    textStyle: TextStyle = TextStyle(
        fontFamily = PitagonsSans,
        color = dgenWhite,
        fontWeight = FontWeight.SemiBold,
        fontSize = body2_fontSize
    ),
    view: View,
    placeholder: @Composable() (() -> Unit)? = null,
    labelContent: @Composable() (() -> Unit)? = null,
    ) {

    var isFocused by remember { mutableStateOf(false) }

    val focusManager = textfieldFocusManager ?: LocalFocusManager.current
    
    // Custom text toolbar for selection menu
    val textToolbarState = remember { SimpleTextToolbarState(activeColor) }
    val customTextToolbar = remember(textToolbarState) { SimpleTextToolbar(textToolbarState) }

    val animatedBackgroundOpacity by animateFloatAsState(
        targetValue = if (isFocused) ghostOpacity else 0f,
        animationSpec = tween(durationMillis = 300),
        label = "backgroundColor"
    )

    val haptics = LocalHapticFeedback.current



    // Click-Outside-Unfocus Handler
    DisposableEffect(view, isFocused) {
        val listener = ViewTreeObserver.OnGlobalFocusChangeListener { _, _ ->
            if (isFocused && !view.hasFocus()) {
                focusManager.clearFocus()
            }
        }

        if (isFocused) {
            view.viewTreeObserver.addOnGlobalFocusChangeListener(listener)
        }

        onDispose {
            view.viewTreeObserver.removeOnGlobalFocusChangeListener(listener)
        }
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape)
            .drawBehind {
                drawRect(
                    color = activeColor,
                    size = size,
                    topLeft = Offset(0f, 0f),
                    alpha = animatedBackgroundOpacity
                )
            }
            .padding(start = 12.dp, end = 12.dp, top = 8.dp, bottom = 8.dp)
            
            .onKeyEvent {
                if (it.nativeKeyEvent.keyCode == Key.Enter.nativeKeyCode) {
                    focusManager.clearFocus()
                    true
                } else {
                    false
                }
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column (
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            horizontalAlignment = Alignment.Start
        ){

            if (labelContent != null) {
                labelContent()
            }

            // Custom selection colors - invisible handles but visible selection
            val customTextSelectionColors = TextSelectionColors(
                handleColor = Color.Transparent,
                backgroundColor = cursorColor.copy(alpha = 0.2f)
            )
            
            // Cursor blinking animation
            val infiniteTransition = rememberInfiniteTransition()
            val cursorAlpha by infiniteTransition.animateFloat(
                initialValue = 1f,
                targetValue = 0f,
                animationSpec = infiniteRepeatable(
                    animation = tween(500, easing = LinearEasing),
                    repeatMode = RepeatMode.Reverse
                ),
                label = "cursorBlink"
            )
            
            var textLayoutResult by remember { mutableStateOf<TextLayoutResult?>(null) }
            var multiLineOverflow by remember { mutableStateOf(false) }

            CompositionLocalProvider(
                LocalTextSelectionColors provides customTextSelectionColors,
                LocalTextToolbar provides customTextToolbar
            ) {
                Box(
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (value.text.isEmpty() && placeholder != null && !isFocused) {
                        placeholder()
                    }
                    
                    BasicTextField(
                        value = value,
                        onValueChange = { newValue ->
                            if ("\n" in newValue.text) {
                                focusManager.clearFocus()
                                onEditDone()
                            } else if (newValue.text.length <= maxLength) {
                                onValueChange(newValue)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = if (multiLineOverflow) 16.dp else 0.dp)
                            .drawBehind {
                                if (isFocused && value.selection.collapsed) {
                                    textLayoutResult?.let { tlr ->
                                        val rect = tlr.getCursorRect(value.selection.start)
                                        val y = ((rect.top + rect.bottom) / 2) - (cursorHeight.toPx() / 2)
                                        drawRect(
                                            color = cursorColor.copy(alpha = cursorAlpha),
                                            topLeft = Offset(rect.left.coerceIn(0f, size.width - cursorWidth.toPx()), y),
                                            size = Size(cursorWidth.toPx(), cursorHeight.toPx())
                                        )
                                    }
                                }
                            }
                            .onFocusChanged { focusState ->
                                if (isFocused && !focusState.isFocused) {
                                    onEditDone()
                                }
                                isFocused = focusState.isFocused
                            },
                        textStyle = textStyle,
                        enabled = enabled,
                        readOnly = readOnly,
                        singleLine = singleLine,
                        minLines = minLines,
                        maxLines = if (singleLine) 1 else maxLines,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = if (singleLine) ImeAction.Done else ImeAction.Default,
                            keyboardType = keyboardtype
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                focusManager.clearFocus()
                                isFocused = false
                            }
                        ),
                        visualTransformation = VisualTransformation.None,
                        cursorBrush = SolidColor(Color.Transparent), // Hide default cursor
                        interactionSource = interactionSource,
                        onTextLayout = {
                            textLayoutResult = it
                            multiLineOverflow = it.lineCount > 1
                        }
                    )
                }
            }
        }
    }
    
    // Render custom selection menu
    SimpleTextSelectionMenuContent(textToolbarState)
}

/**
 * State holder for custom text toolbar
 */
@Stable
class SimpleTextToolbarState(
    private val primaryColor: Color = dgenTurqoise
) {
    var isShowing by mutableStateOf(false)
        private set
    var menuRect by mutableStateOf(Rect.Zero)
        private set
    var onCopy: (() -> Unit)? by mutableStateOf(null)
        private set
    var onPaste: (() -> Unit)? by mutableStateOf(null)
        private set
    var onCut: (() -> Unit)? by mutableStateOf(null)
        private set
    var onSelectAll: (() -> Unit)? by mutableStateOf(null)
        private set

    fun show(
        rect: Rect,
        onCopyRequested: (() -> Unit)?,
        onPasteRequested: (() -> Unit)?,
        onCutRequested: (() -> Unit)?,
        onSelectAllRequested: (() -> Unit)?
    ) {
        menuRect = rect
        onCopy = onCopyRequested
        onPaste = onPasteRequested
        onCut = onCutRequested
        onSelectAll = onSelectAllRequested
        isShowing = true
    }

    fun hide() {
        isShowing = false
    }
}

/**
 * Custom Text Toolbar implementation
 */
class SimpleTextToolbar(
    private val state: SimpleTextToolbarState
) : TextToolbar {
    
    override val status: TextToolbarStatus
        get() = if (state.isShowing) TextToolbarStatus.Shown else TextToolbarStatus.Hidden

    override fun hide() {
        state.hide()
    }

    override fun showMenu(
        rect: Rect,
        onCopyRequested: (() -> Unit)?,
        onPasteRequested: (() -> Unit)?,
        onCutRequested: (() -> Unit)?,
        onSelectAllRequested: (() -> Unit)?
    ) {
        if (!state.isShowing) {
            state.show(rect, onCopyRequested, onPasteRequested, onCutRequested, onSelectAllRequested)
        }
    }
}

@Composable
fun SimpleTextSelectionMenuContent(state: SimpleTextToolbarState) {
    if (state.isShowing) {
        ComposeDisposableEffect(state.isShowing) {
            onDispose {
                // Cleanup if needed
            }
        }
        
        SimpleSelectionMenu(
            rect = state.menuRect,
            onCopy = state.onCopy,
            onPaste = state.onPaste,
            onCut = state.onCut,
            onSelectAll = state.onSelectAll,
            onDismiss = { state.hide() },
            primaryColor = SystemColorManager.primaryColor
        )
    }
}

@Composable
fun SimpleSelectionMenu(
    rect: Rect,
    onCopy: (() -> Unit)?,
    onPaste: (() -> Unit)?,
    onCut: (() -> Unit)?,
    onSelectAll: (() -> Unit)?,
    onDismiss: () -> Unit,
    primaryColor: Color = SystemColorManager.primaryColor
) {
    // Get screen dimensions
    val configuration = androidx.compose.ui.platform.LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val density = androidx.compose.ui.platform.LocalDensity.current
    
    // Calculate actual menu dimensions based on buttons
    val buttonCount = listOfNotNull(onCut, onCopy, onPaste, onSelectAll).size
    val menuWidth = (buttonCount * 50 + 20).dp // Approximate width based on button count
    val menuHeight = 44.dp // Reduced height for better fit
    val menuWidthPx = with(density) { menuWidth.toPx() }
    val menuHeightPx = with(density) { menuHeight.toPx() }
    val screenWidthPx = with(density) { screenWidth.toPx() }
    val screenHeightPx = with(density) { screenHeight.toPx() }
    
    // Much larger spacing to ensure text is always visible
    val preferredSpacing = with(density) { 80.dp.toPx() }  // Extra large spacing - text will definitely be visible
    val minimalSpacing = with(density) { 60.dp.toPx() }    // Even in tight spaces, keep good distance
    val edgePadding = with(density) { 20.dp.toPx() }       // Increased edge padding too
    
    // Calculate horizontal position
    var xOffset = if (rect.width > 0) {
        // Center the menu over the selection
        (rect.left + rect.width / 2 - menuWidthPx / 2).toInt()
    } else {
        // Position at cursor for paste on empty field
        (rect.left - menuWidthPx / 2).toInt()
    }
    
    // Ensure menu stays within screen bounds horizontally with smart adjustment
    if (xOffset < edgePadding) {
        // Too close to left edge
        xOffset = edgePadding.toInt()
    } else if (xOffset + menuWidthPx > screenWidthPx - edgePadding) {
        // Too close to right edge
        xOffset = (screenWidthPx - menuWidthPx - edgePadding).toInt()
    }
    
    // Calculate vertical position with preference for above to avoid covering text
    val spaceAbove = rect.top
    val spaceBelow = screenHeightPx - rect.bottom
    val textHeight = rect.height
    
    val yOffset: Int
    
    // Prefer placing above the selection to avoid covering text
    if (spaceAbove >= menuHeightPx + preferredSpacing) {
        // Place above with good spacing
        yOffset = (rect.top - menuHeightPx - preferredSpacing).toInt()
    } else if (spaceBelow >= menuHeightPx + preferredSpacing && textHeight < with(density) { 100.dp.toPx() }) {
        // Place below only if text is not too tall (to avoid covering multi-line selections)
        yOffset = (rect.bottom + preferredSpacing).toInt()
    } else if (spaceAbove > menuHeightPx + minimalSpacing) {
        // Place above with minimal spacing if needed
        yOffset = (rect.top - menuHeightPx - minimalSpacing).toInt()
    } else if (spaceBelow > menuHeightPx + minimalSpacing) {
        // Place below with minimal spacing
        yOffset = (rect.bottom + minimalSpacing).toInt()
    } else {
        // Last resort: place at top or bottom of screen with extra spacing from text
        val rectCenterY = rect.top + rect.height / 2
        if (rectCenterY < screenHeightPx / 2) {
            // Selection is in top half, place menu at bottom
            yOffset = (screenHeightPx - menuHeightPx - edgePadding).toInt()
        } else {
            // Selection is in bottom half, place menu at top
            yOffset = edgePadding.toInt()
        }
    }
    
    val secondaryColor = SystemColorManager.secondaryColor
    
    Popup(
        alignment = Alignment.TopStart,
        offset = androidx.compose.ui.unit.IntOffset(xOffset, yOffset),
        onDismissRequest = onDismiss,
        properties = PopupProperties(
            focusable = false,
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Card(
            modifier = Modifier
                .shadow(8.dp, CardRoundedCornerShape(12.dp))
                .background(secondaryColor, CardRoundedCornerShape(12.dp)),
            colors = CardDefaults.cardColors(
                containerColor = secondaryColor
            ),
            shape = CardRoundedCornerShape(12.dp)
        ) {
            MaterialRow(
                modifier = Modifier.padding(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                onCut?.let {
                    SimpleTextSelectionMenuButton(
                        text = "CUT",
                        onClick = {
                            it()
                            onDismiss()
                        },
                        primaryColor = primaryColor
                    )
                }
                
                onCopy?.let {
                    SimpleTextSelectionMenuButton(
                        text = "COPY",
                        onClick = {
                            it()
                            onDismiss()
                        },
                        primaryColor = primaryColor
                    )
                }
                
                onPaste?.let {
                    SimpleTextSelectionMenuButton(
                        text = "PASTE",
                        onClick = {
                            it()
                            onDismiss()
                        },
                        primaryColor = primaryColor
                    )
                }
                
                onSelectAll?.let {
                    SimpleTextSelectionMenuButton(
                        text = "ALL",
                        onClick = {
                            it()
                            onDismiss()
                        },
                        primaryColor = primaryColor
                    )
                }
            }
        }
    }
}

@Composable
private fun SimpleTextSelectionMenuButton(
    text: String,
    onClick: () -> Unit,
    primaryColor: Color
) {
    TextButton(
        onClick = onClick,
        modifier = Modifier.height(36.dp),
        contentPadding = PaddingValues(horizontal = 12.dp),
        colors = ButtonDefaults.textButtonColors(
            contentColor = primaryColor
        )
    ) {
        MaterialText(
            text = text,
            style = TextStyle(
                fontFamily = SpaceMono,
                fontSize = label_fontSize,
                fontWeight = FontWeight.SemiBold
            ),
            color = primaryColor
        )
    }
}