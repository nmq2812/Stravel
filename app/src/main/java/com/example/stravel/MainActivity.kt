package com.example.stravel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import androidx.navigation.navArgument
import com.example.stravel.Screen.DetailScreen
import com.example.stravel.Screen.FavouriteScreen
import com.example.stravel.Screen.HomeScreen
import com.example.stravel.Screen.LoginScreen
import com.example.stravel.Screen.Screens
import com.example.stravel.Screen.SettingScreen
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
    val navController = rememberNavController()
    val myGraph = navController.createGraph(
        startDestination = Screens.HomeScreen.name, // ID của điểm đến bắt đầu
        //route = Screens.HomeScreen.name // Tuyến đường của NavGraph
    ) {
        // Thêm các điểm đến vào NavGraph
        composable(Screens.HomeScreen.name) { HomeScreen(navController) }
        composable(Screens.FavouriteScreen.name) { FavouriteScreen(navController) }
        composable(Screens.SettingScreen.name) { SettingScreen(navController) }
        composable(Screens.LoginScreen.name) { LoginScreen() }
        composable(
            route = Screens.DetailScreen.name + "/{placeId}",
            arguments = listOf(
                navArgument(name = "placeId") {
                    type = NavType.IntType
                }
            )
        ) {
            DetailScreen(navController, it)
        }
    }
    AppNavigation(navController, myGraph)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StravelTheme {
        Greeting()
    }
}