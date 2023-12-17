package org.ethosmobile.components.library.mint

import android.widget.EditText
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.ethosmobile.components.library.theme.Colors
import org.ethosmobile.components.library.theme.Font


@Composable
fun InputField(
    value: String,
    placeholder: String,
    readOnly: Boolean = false,
    singeLine: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    size: Int = 20,
    //shape: Shape = RoundedCornerShape(12.dp),
    modifier: Modifier = Modifier,
    trailingIcon: @Composable (() -> Unit)? = null,
    onChange: ((value: String) -> Unit)? = null
) {
    
    var text by remember { mutableStateOf(value) }
    TextField(
        value = text,
        onValueChange = {
            text = it
            if (onChange != null) {
                onChange(it)
            }
        },
        textStyle = TextStyle(
            fontFamily =  Font.INTER,
            fontSize = size.sp,
            fontWeight = FontWeight.SemiBold
        ),
        placeholder = {
            Text(
                text = placeholder,
                fontFamily = Font.INTER,
                fontSize = size.sp,
                fontWeight = FontWeight.SemiBold
            )
        },
        readOnly = readOnly,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Colors.TRANSPARENT,
            textColor = Colors.WHITE,
            placeholderColor = Colors.GRAY,
            errorCursorColor= Colors.ERROR,
            unfocusedIndicatorColor = Colors.TRANSPARENT,
            focusedIndicatorColor = Colors.TRANSPARENT,
            cursorColor = Colors.WHITE
        ),
        shape = RoundedCornerShape(12.dp),
        modifier = modifier,
        singleLine = singeLine,
        maxLines = maxLines,
        trailingIcon = trailingIcon
    )
}

@Composable
@Preview
fun PreviewInputFiledBox() {
    
        InputField("Test", "Test", modifier = Modifier.fillMaxWidth())
    
}

/*

val customTextFieldColors = TextFieldColors(
    backgroundColor = Color.Black,
    cursorColor = Color.Green,
    focusedIndicatorColor = Color.Blue,
    unfocusedIndicatorColor = Color.Gray,
    disabledLabelColor = Color.Gray,
    errorLabelColor = Color.Red,
    leadingIconColor = Color.White,
    trailingIconColor = Color.White,
    textColor = Color.White
)

@Composable
@Preview
fun PreviewInputFiledEmpty() {
    NftCreatorTheme {
        InputField("") {
            c
        }
    }
}

@Composable
@Preview
fun PreviewInputFiled() {
    NftCreatorTheme {
        InputField("Test") {
            println(it)
        }
    }
}

@Composable
@Preview
fun PreviewInputFiledBox() {
    NftCreatorTheme {
        InputField(
            "Test",
            singeLine = false,
            maxLines = 5,
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.7f)
        ) {
            println(it)
        }
    }
}*/