package com.example.ethoscomponents.components

//import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ethoscomponents.theme.Color
import com.example.ethoscomponents.theme.Font


@Composable
fun ethOSTextfield(
    value: String,
    placeholder: String,
    readOnly: Boolean = false,
    singeLine: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    modifier: Modifier = Modifier,
    trailingIcon: @Composable (() -> Unit)? = null,
    onChange: ((value: String) -> Unit)? = null) {

    var text by remember { mutableStateOf(value) }

    TextField(
        value = text,
        onValueChange = {
            text = it
            if (onChange != null) {
                onChange(it)
            }
        },
        placeholder = {
            Text(
                text = placeholder,
                fontFamily = Font.PRIMARY,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp
            )
        },
        textStyle = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.SemiBold),
        readOnly = readOnly,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.BLUE,
            textColor = Color.WHITE,
            placeholderColor = Color.GRAY,
            errorCursorColor= Color.ERROR,
            unfocusedIndicatorColor = Color.TRANSPARENT,
            focusedIndicatorColor = Color.TRANSPARENT
        ),
        shape = RoundedCornerShape(12.dp),
        modifier = modifier,
        singleLine = singeLine,
        maxLines = maxLines,
        trailingIcon = trailingIcon
    )
}

/*@Preview(showBackground = true)
@Composable
fun ethOSTextfieldPreview() {
    ethOSTheme {
        /*Column() {
            ethOSTextfield(value="",placeholder="Amount")
        }*/
        ethOSTextfield(value = "", placeholder = "Placeholder")

    }
}*/