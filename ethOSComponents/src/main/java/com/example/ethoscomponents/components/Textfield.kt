package com.example.componentlibrary.ui.components



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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.componentlibrary.ui.theme.ethOSTheme
import com.example.componentlibrary.ui.theme.blue
import com.example.componentlibrary.ui.theme.gray
import com.example.componentlibrary.ui.theme.white
import com.example.componentlibrary.ui.theme.error
import com.example.ethoscomponents.R


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

    val Inter = FontFamily(
        Font(R.font.inter_light, FontWeight.Light),
        Font(R.font.inter_regular, FontWeight.Normal),
        Font(R.font.inter_medium, FontWeight.Medium),
        Font(R.font.inter_semibold, FontWeight.SemiBold),
        Font(R.font.inter_bold, FontWeight.Bold)
    )
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
                fontFamily = Inter,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp
            )
        },
        textStyle = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.SemiBold),
        readOnly = readOnly,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = blue,
            textColor = white,
            placeholderColor = gray,
            errorCursorColor= error,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
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