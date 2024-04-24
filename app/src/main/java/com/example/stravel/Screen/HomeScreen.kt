package com.example.stravel.Screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stravel.components.HomeContent
import com.example.stravel.components.SearchBar

@Composable
fun HomeScreen() {
    Box(modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column() {
                Text(
                    "Explore",
                    style = TextStyle(fontSize = 16.sp),
                    modifier = Modifier
                        .padding(start = 16.dp)
                )
                Text(
                    "STRAVEL",
                    style = TextStyle(fontSize = 48.sp),
                    modifier = Modifier
                        .padding(16.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            Box(modifier = Modifier.fillMaxWidth()) {
                SearchBar()
            }

            Column(modifier = Modifier.fillMaxSize()) {
                HomeContent()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    HomeScreen()
}