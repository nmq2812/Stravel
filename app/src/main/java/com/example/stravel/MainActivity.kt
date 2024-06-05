package com.example.stravel

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stravel.components.AppNavigation
import com.example.stravel.screen.LoginScreen
import com.example.stravel.screen.RegisterScreen
import com.example.stravel.ui.theme.StravelTheme
import com.example.stravel.viewmodel.MainViewModel
import com.example.stravel.viewmodel.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

private val auth: FirebaseAuth = Firebase.auth

// ...
class MainActivity : ComponentActivity() {
    private val mainViewModel by viewModels<MainViewModel>()
    private val userViewModel by viewModels<UserViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StravelTheme {
                Greeting(mainViewModel, userViewModel)
            }

            //khóa xoay màn hình
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        }

    }
}



@Composable
fun Greeting(mainViewModel: MainViewModel, userViewModel: UserViewModel) {
    val mainController = rememberNavController()
    NavHost(mainController, "main") {
        composable("login") { LoginScreen(mainController, auth, userViewModel) }
        composable("register") { RegisterScreen(mainController, auth, userViewModel) }
        composable("main") {
            AppNavigation(
                mainController,
                mainViewModel.listOfPlaceItems,
                userViewModel.userAccounts
            )
        }
    }
}

