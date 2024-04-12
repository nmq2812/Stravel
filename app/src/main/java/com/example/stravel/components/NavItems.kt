package com.example.stravel.components

import com.example.stravel.R
import com.example.stravel.Screen.Screens

data class NavItems(
    val iconSelected: Int,
    val iconNotSelected: Int,
    val route: String
)

val listOfNavItems = listOf(
    NavItems(
        iconSelected = R.drawable.home_24,
        iconNotSelected = R.drawable.home_24_outline,
        route = Screens.HomeScreen.name
    ),
    NavItems(
        iconSelected = R.drawable.heart_24,
        iconNotSelected = R.drawable.heart_24_outline,
        route = Screens.FavouriteScreen.name
    ),
    NavItems(
        iconSelected = R.drawable.user_24,
        iconNotSelected = R.drawable.user_24_outline,
        route = Screens.SettingScreen.name
    )
)