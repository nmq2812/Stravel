package com.example.stravel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stravel.components.AppNavigation
import com.example.stravel.components.PlaceItem
import com.example.stravel.components.viewmodel.MainViewModel
import com.example.stravel.screen.LoginScreen
import com.example.stravel.screen.RegisterScreen
import com.example.stravel.ui.theme.StravelTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

private lateinit var auth: FirebaseAuth
private lateinit var placeItemReference: DatabaseReference
private lateinit var viewModel: MainViewModel

// ...
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
            viewModel.getPlaceItemList()
            val listOfPlaceItems: MutableList<PlaceItem> = viewModel.listOfPlaceItems
            auth = Firebase.auth
            placeItemReference = Firebase.database.getReference("PlaceItem")
            StravelTheme {
                Greeting(listOfPlaceItems)
            }

        }

    }
}



@Composable
fun Greeting(listOfPlaceItems: MutableList<PlaceItem>) {
    val mainController = rememberNavController()
    NavHost(mainController,"main") {
        composable("login") { LoginScreen(mainController, auth) }
        composable("register") { RegisterScreen(mainController, auth) }
        composable("main") {AppNavigation(mainController,listOfPlaceItems)}
    }
}

