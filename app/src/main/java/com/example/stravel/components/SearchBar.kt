package com.example.stravel.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.stravel.R


@Composable
fun SearchBar(value: String, onSearch: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 3.dp, shape = RoundedCornerShape(100.dp), spotColor = Color.Gray),
    ) {
        Icon(
            painter = painterResource(id = R.drawable.search_24),
            contentDescription = null,
            modifier = Modifier.padding(top = 16.dp, start = 16.dp)
        )
        TextField(
            value = value,
            onValueChange = onSearch,
            shape = RoundedCornerShape(10.dp),
            placeholder = {
                Row {
                    Text("Tìm kiếm địa điểm")
                }
            },
//            trailingIcon = {
//                if (value != "") {
//                    IconButton(onClick = {value = ""}) {
//                        Icon(Icons.Default.Clear, contentDescription = null)
//                    }
//                }
//            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent, // Đặt màu viền khi focus là trong suốt
                unfocusedBorderColor = Color.Transparent, // Đặt màu viền khi unfocus là trong suốt
            ),
            modifier = Modifier
                .weight(1f)
                .background(color = Color.Transparent)
        )
    }
}
