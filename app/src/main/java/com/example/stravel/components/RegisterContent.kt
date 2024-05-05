package com.example.stravel.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.stravel.ui.theme.CardColor

@Composable
fun RegisterContent(
    mainController: NavHostController
) {
    var emailValue by remember{ mutableStateOf("") }
    var passwordValue by remember{ mutableStateOf("") }
    val customShape = RoundedCornerShape(4.dp)

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "ĐĂNG KÝ",
            fontSize = 36.sp,
            modifier = Modifier.padding(top = 48.dp, bottom = 24.dp)
        )
        Spacer(modifier = Modifier.padding(8.dp))
        TextField(
            value = emailValue,
            onValueChange = { emailValue = it },
            placeholder = {
                Row {
                    Text("Nhập Email")
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions {},
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = CardColor,
                unfocusedBorderColor = Color.Transparent
            ),
            modifier = Modifier
                .background(color = Color.Transparent)
                .fillMaxWidth(0.7f)
                .shadow(elevation = 1.dp, shape = RoundedCornerShape(16.dp), spotColor = CardColor),
        )
        Spacer(modifier = Modifier.padding(8.dp))
        TextField(
            value = passwordValue,
            onValueChange = { passwordValue = it },
            placeholder = {
                Row {
                    Text("Nhập mật khẩu")
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = CardColor,
                unfocusedBorderColor = Color.Transparent
            ),
            modifier = Modifier
                .background(color = Color.Transparent)
                .fillMaxWidth(0.7f)
                .shadow(elevation = 1.dp, shape = RoundedCornerShape(16.dp), spotColor = CardColor),
        )
        Spacer(modifier = Modifier.padding(8.dp))
        TextField(
            value = passwordValue,
            onValueChange = { passwordValue = it },
            placeholder = {
                Row {
                    Text("Nhập lại mật khẩu")
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions { /* Xử lý khi nhấn Done */ },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = CardColor,
                unfocusedBorderColor = Color.Transparent
            ),
            modifier = Modifier
                .background(color = Color.Transparent)
                .fillMaxWidth(0.7f)
                .shadow(elevation = 1.dp, shape = RoundedCornerShape(16.dp), spotColor = CardColor),
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(0.7f)
        ) {
            Text(
                text = "Quay lại",
                color = CardColor,
                modifier = Modifier
                    .clickable {
                        mainController.navigateUp()
                    }
            )
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Button(
            onClick = {},
            shape = customShape,
            modifier = Modifier.fillMaxWidth(0.7f)
        ) {
            Text("Register")
        }

        Spacer(modifier = Modifier.padding(8.dp))

        Text(
            text = "Đã có tài khoản? Đăng nhập",
            color = Color.Blue,
            modifier = Modifier.clickable {
                mainController.navigateUp()
            }
        )
    }
}
