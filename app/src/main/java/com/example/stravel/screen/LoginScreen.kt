package com.example.stravel.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.stravel.components.LoginContent
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(mainController: NavHostController, auth: FirebaseAuth) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LoginContent(mainController, auth)
    }
}