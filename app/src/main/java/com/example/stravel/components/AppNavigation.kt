package com.example.stravel.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import androidx.navigation.navArgument
import com.example.stravel.screen.AccountScreen
import com.example.stravel.screen.DetailScreen
import com.example.stravel.screen.FavouriteScreen
import com.example.stravel.screen.HomeScreen
import com.example.stravel.screen.PaymentScreen
import com.example.stravel.screen.Screens
import com.example.stravel.screen.SettingScreen
import com.example.stravel.ui.theme.CardColor

lateinit var listOfPlaceItems: MutableList<PlaceItem>

@Composable
fun AppNavigation(mainController: NavHostController) {
    listOfPlaceItems = getPlaceItemList()
    val navController = rememberNavController()
    val mainGraph = navController.createGraph(
        startDestination = Screens.HomeScreen.name, // ID của điểm đến bắt đầu
    ) {
        // Thêm các điểm đến vào NavGraph
        composable(Screens.HomeScreen.name) { HomeScreen(navController, listOfPlaceItems) }
        composable(Screens.FavouriteScreen.name) { FavouriteScreen(navController, listOfPlaceItems) }
        composable(Screens.SettingScreen.name) { SettingScreen(navController) }
        composable(Screens.AccountScreen.name) { AccountScreen(mainController, navController) }

        composable(
            route = Screens.PaymentScreen.name + "/{placeId}",
            arguments = listOf(
                navArgument(name = "placeId") {
                    type = NavType.IntType
                }
            )
        ) {
            PaymentScreen(navController, it, listOfPlaceItems)
        }
        composable(
            route = Screens.DetailScreen.name + "/{placeId}",
            arguments = listOf(
                navArgument(name = "placeId") {
                    type = NavType.IntType
                }
            )
        ) {
            DetailScreen(navController, it, listOfPlaceItems)
        }
    }
    Scaffold (
        containerColor = Color.Transparent,
        modifier = Modifier
            .background(color = Color.Transparent)
            .padding(8.dp),
        bottomBar = {
            val mainColor = CardColor
            ElevatedCard(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 16.dp
                ),
                modifier = Modifier.clip(RoundedCornerShape(100.dp))
            ) {
                NavigationBar(
                    containerColor = Color.Transparent,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent)
                        .padding(bottom = 8.dp, top = 4.dp),
                    content = {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentDestination = navBackStackEntry?.destination
                        listOfNavItems.forEach {navItem ->
                            val condition = currentDestination?.hierarchy?.any {it.route == navItem.route} == true
                            NavigationBarItem(
                                modifier = Modifier.background(Color.Transparent),
                                selected = condition,
                                colors = NavigationBarItemColors(
                                    selectedIconColor = mainColor,
                                    selectedTextColor = Color.Transparent,
                                    disabledIconColor = mainColor,
                                    disabledTextColor = Color.Transparent,
                                    unselectedIconColor = Color.Gray,
                                    unselectedTextColor = Color.Transparent,
                                    selectedIndicatorColor = Color.Transparent,
                                ),
                                onClick = {
                                    navController.navigate(navItem.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = false
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                },
                                icon = {
                                    if (condition) {
                                        Icon(painter = painterResource(id = navItem.iconSelected), contentDescription = null, tint = mainColor)
                                    } else {
                                        Icon(painter = painterResource(id = navItem.iconNotSelected), contentDescription = null)
                                    }
                                }
                            )
                        }
                    }
                )
            }

        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            graph = mainGraph,
            modifier = Modifier
                .padding(paddingValues)
        )
    }
}


