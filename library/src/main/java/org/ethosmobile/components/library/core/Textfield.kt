package org.ethosmobile.components.library.core

//import androidx.compose.ui.tooling.preview.Preview
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.ethosmobile.components.library.theme.Colors
import org.ethosmobile.components.library.theme.Fonts
import kotlin.math.max


@Composable
fun ethOSCenterTextField(
    text: String,
    size: Int,
    //focusRequester: FocusRequester? = null,
    modifier: Modifier = Modifier,
    label: String = "",
    singleLine: Boolean = false,//true,
    onTextChanged: (String) -> Unit,
    color: Color = Color.White
) {

    var isFocused by remember { mutableStateOf(false) }
    //val focusRequester = FocusRequester()
    var fontSize by remember { mutableStateOf(size.sp) }



    BasicTextField(
        value = text,
        onValueChange = {

            onTextChanged(it)


        },
        singleLine = singleLine,
        minLines = 1,
        maxLines = 2,
        textStyle = LocalTextStyle.current.copy(
            fontFamily = Fonts.INTER,
            textAlign = TextAlign.Center,
            color = color,
            fontSize = fontSize,
            fontWeight = FontWeight.SemiBold,
        ),
        modifier = Modifier.fillMaxWidth(),

//            textStyle = TextStyle(
////                textAlign = TextAlign.Center,
////                fontSize =  24.sp,
////                fontWeight = FontWeight.SemiBold,
////            ),
        cursorBrush = SolidColor(Color.White),
//            modifier = modifier
//                .clip(RoundedCornerShape(10))
//                .background(Color.Magenta)
//                .height(64.dp)


    ) { innerTextField ->
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            if (!isFocused && text.isEmpty()) {
                Text(
                    text = label,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium,
                    fontSize = size.sp,
                    color = Colors.GRAY,
                    modifier = Modifier.fillMaxWidth()



                )
            }
            innerTextField()
        }
    }


}

@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("SuspiciousIndentation")
@Composable
fun ethOSTextField(
    text: String,
    size: Int,
    modifier: Modifier = Modifier,
    label: String = "",
    singleLine: Boolean = false,//true,
    maxChar: Int = 42,
    sizeCut: Int = 2,
    numberInput: Boolean = false,
    onTextChanged: (String) -> Unit,
    color: Color = Color.White,
    center: Boolean = false,
) {

    var isFocused by remember { mutableStateOf(false) }
    val focusRequester = FocusRequester()
    var fontSize by remember { mutableStateOf(size.sp) }
    //val keyboardController = LocalSoftwareKeyboardController.current


    val context = LocalContext.current


    BasicTextField(
        value = text,
        readOnly = false,
        onValueChange = {
            if(it.length < maxChar){
                onTextChanged(it)
            }
            fontSize = size.sp
        },
        keyboardActions = KeyboardActions(
            onDone = {
                // Clear focus when the user presses the "Done" button on the keyboard
//                    focusManager.clearFocus()
                focusRequester.requestFocus()
                //Toast.makeText(context,"Hallo",Toast.LENGTH_LONG).show()

                //keyboardController?.hide() // Hide the keyboard
            }
        ),
        keyboardOptions = if(numberInput) KeyboardOptions(keyboardType = KeyboardType.Number,imeAction = ImeAction.Done) else KeyboardOptions(keyboardType = KeyboardType.Text,imeAction = ImeAction.Done),
        singleLine = singleLine,
        minLines = 1,
        maxLines = 2,
        textStyle = LocalTextStyle.current.copy(
            color = color,
            fontSize = calculateFontSize(text.length,size,sizeCut),
            fontWeight = FontWeight.SemiBold
        ),

        modifier = modifier
            .focusRequester(focusRequester)
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            }
            .width(IntrinsicSize.Min)
            .onKeyEvent {
                if (it.nativeKeyEvent.keyCode.toLong() == Key.NumPadEnter.keyCode){
                    focusRequester.requestFocus()
                    true
                }
                false
            },
        cursorBrush = SolidColor(if (isFocused) Color.White else Color.Green),
    ) { innerTextField ->
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = modifier
                .fillMaxWidth()
        ) {
            if (!isFocused && text.isEmpty()) {
                Text(
                    text = label,
                    textAlign = if(center) TextAlign.Center else TextAlign.Start ,
                    fontWeight = FontWeight.Medium,
                    fontSize = size.sp,
                    color = Color(0xFF9FA2A5),
                    modifier = modifier.fillMaxWidth()
                )
            }
            innerTextField()
        }
    }





}


@Composable
fun ethOSFilledIconTextField(
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    focusedContainerColor: Color = Colors.TRANSPARENT,
    unfocusedContainerColor: Color = Colors.TRANSPARENT,
    focusedTextColor: Color = Colors.WHITE,
    unfocusedTextColor: Color = Colors.WHITE,
    cursorColor: Color = Colors.WHITE,
    focusedIndicatorColor: Color = Colors.GRAY

) {

    TextField(
        colors= TextFieldDefaults.colors(
            focusedContainerColor = focusedContainerColor,
            unfocusedContainerColor = unfocusedContainerColor,
            focusedTextColor = focusedTextColor,
            unfocusedTextColor = unfocusedTextColor,
            cursorColor = cursorColor,
            focusedIndicatorColor = focusedIndicatorColor
        ),
        textStyle = LocalTextStyle.current.copy(
            color = Colors.WHITE,

            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        ),
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        shape = RoundedCornerShape(8.dp),
        placeholder = placeholder,
        keyboardOptions = keyboardOptions
    )


}

fun calculateFontSize(length: Int, defaultSize: Int, maxChar: Int ): TextUnit {
    val defaultFontSize = defaultSize.sp
    val maxChars = maxChar
    val scaleFactor = 0.8

    var adjustedSize = if (length > maxChars) {
        val scaledSize = (defaultFontSize * scaleFactor).value
        val minValue = 12f // Minimum font size
        val result = max(scaledSize, minValue)
        result.sp
    } else {
        defaultFontSize
    }

    if (length > maxChars*2) {
        val scaledSize = (adjustedSize * scaleFactor).value
        val minValue = 12f // Minimum font size
        val result = max(scaledSize, minValue)
        adjustedSize = result.sp
        Log.e("Length: ", "${ adjustedSize }")
    }

    if (length > maxChars*3) {
        val scaledSize = (adjustedSize * scaleFactor).value
        val minValue = 12f // Minimum font size
        val result = max(scaledSize, minValue)
        adjustedSize = result.sp
        Log.e("Length: ", "${ adjustedSize }")
    }

//    if (length > (maxChars*2) - 15) {
//        val scaledSize = (adjustedSize * scaleFactor).value
//        val minValue = 12f // Minimum font size
//        val result = max(scaledSize, minValue)
//        adjustedSize = result.sp
//    }
    return adjustedSize
}



@Preview
@Composable
fun PreviewTextField() {

    var test by remember { mutableStateOf("") }//0x2ade3187F051796542aF4f320c430fF9Bdd9507F
    val focusRequester = remember { FocusRequester() }
    //val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        ethOSCenterTextField(
            text = test,
            label = "(Enter address, ENS or QR scan)",
            modifier = Modifier.weight(1f), //.background(Color.Red),//.fillMaxWidth(0.95f).
            singleLine = false,
            onTextChanged = { value -> test = value },
                    size = 20,


//                modifier = Modifier.fillMaxWidth(),



        )
        //TextField(value = test, onValueChange = {test = it})
    }

}
