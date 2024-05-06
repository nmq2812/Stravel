package com.example.stravel.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.stravel.components.HomeContent
import com.example.stravel.components.SearchBar
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

@Composable
fun HomeScreen(navController: NavHostController) {
    var searchBarValue by remember { mutableStateOf("") }
    Box(modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column() {
                var appName by remember { mutableStateOf("") }
                val database = Firebase.database
                val myRef = database.getReference("title")
                myRef.child("name").get().addOnSuccessListener {
                    appName = it.value.toString()
                }

                Text(
                    appName.uppercase(),
                    style = TextStyle(fontSize = 48.sp),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(8.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(modifier = Modifier.fillMaxWidth()) {
                SearchBar(
                    value = searchBarValue, // Pass the searchBarValue directly
                    onSearch = { newValue -> searchBarValue = newValue }
                )
            }

            Column(modifier = Modifier.fillMaxSize()) {
                HomeContent(navController, searchBarValue)
            }
        }
    }
}
