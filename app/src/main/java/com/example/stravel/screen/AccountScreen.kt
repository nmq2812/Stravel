package com.example.stravel.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.stravel.components.AccountContent


@Composable
fun AccountScreen(mainController: NavController) {
    AccountContent(mainController)
}