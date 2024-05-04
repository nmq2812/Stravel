package com.example.stravel.Screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun AccountScreen(navController: NavController) {
    Box(modifier = Modifier
        .fillMaxSize()
        ,contentAlignment = Alignment.Center
    ) {
        Button(onClick = {navController.navigate(Screens.LoginScreen.name)}) {
            Text("Login/Sign up")
        }
    }
}