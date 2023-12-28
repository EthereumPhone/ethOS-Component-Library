package org.ethosmobile.components.library.core

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ethOSEditTextfield(
    title: String,
    value: String,
    important: Boolean,
    error: MutableState<Boolean>,
    onChange: (String) -> Unit,
    onlyNumbers: Boolean = false,
    onlyEmail: Boolean = false,

    ){

    var keyboardtype: KeyboardType = KeyboardType.Text
    if(onlyNumbers){
        keyboardtype = KeyboardType.Phone
    }
    if(onlyEmail){
        keyboardtype = KeyboardType.Email
    }

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color.Transparent
            )
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(18.dp),
        verticalAlignment = Alignment.CenterVertically

    ){

        Column (
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Text(
                text = title + if(important) " *" else "",
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                color = if (error.value)  Color.Red else Color.Gray
            )

            TextField(
                value = value,
                onValueChange = onChange,
                placeholder = {
                    Text(
                        text = "",
                        //fontFamily = Font.PRIMARY,
                        fontWeight = FontWeight.Normal,
                        fontSize = 24.sp
                    )
                },
                textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold),

                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xFF1a1a1a),
                    focusedTextColor = Color.White,
                    errorCursorColor= Color.Red,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions(keyboardType = keyboardtype),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                maxLines = 1,
            )
        }
    }

}