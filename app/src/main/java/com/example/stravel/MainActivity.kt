package com.example.stravel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stravel.screen.LoginScreen
import com.example.stravel.screen.RegisterScreen
import com.example.stravel.components.AppNavigation
import com.example.stravel.ui.theme.StravelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           StravelTheme {
               Greeting()
           }
       }
    }
}

@Composable
fun Greeting() {
    val mainController = rememberNavController()
    NavHost(mainController,"main") {
        composable("main") {AppNavigation(mainController)}
        composable("login") { LoginScreen(mainController)}
        composable("register") {RegisterScreen(mainController)}
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StravelTheme {
        Greeting()
    }
}