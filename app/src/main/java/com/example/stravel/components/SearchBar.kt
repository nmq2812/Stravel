package com.example.stravel.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.stravel.R
import com.example.stravel.ui.theme.CardColor


@Composable
fun SearchBar() {
    var searchValue by remember{ mutableStateOf("") }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Transparent)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.search_24),
            contentDescription = null,
            modifier = Modifier.padding(top = 16.dp, start = 16.dp)
        )
        OutlinedTextField(
            value = searchValue,
            onValueChange = { searchValue = it },
            placeholder = {
                Row {
                    Text("Tìm kiếm địa điểm")
                }
                    },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = CardColor, // Đặt màu viền khi focus là xanh lá
                unfocusedBorderColor = Color.Transparent // Đặt màu viền khi unfocus là đỏ
            ),
            modifier = Modifier
                .weight(1f)
                .background(color = Color.Transparent)
                .shadow(elevation = 0.dp, shape = RoundedCornerShape(10.dp))
        )
    }
}
