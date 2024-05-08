package com.example.stravel.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.stravel.components.FavouriteContent
import com.example.stravel.components.PlaceItem

@Composable
fun FavouriteScreen(navController: NavHostController, listOfPlaceItems: List<PlaceItem>) {
    Box(modifier = Modifier
        .fillMaxSize()
        ,contentAlignment = Alignment.Center
    ) {
        FavouriteContent(navController, listOfPlaceItems)
    }
}