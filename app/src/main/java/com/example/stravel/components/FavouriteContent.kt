package com.example.stravel.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun FavouriteContent(navController: NavHostController) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxWidth()
    ) {
        listOfPlaceItems.filter{it.favou}.forEach {placeItem ->
            items(1) {
                Column(modifier = Modifier.fillMaxHeight()) {
                    GridItem(placeItem, navController)
                }
            }
        }
    }
}
