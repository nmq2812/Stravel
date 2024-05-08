package com.example.stravel.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun FavouriteContent(navController: NavHostController, listOfPlaceItems: MutableList<PlaceItem>?) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxWidth()
    ) {
        if (listOfPlaceItems != null) {
            listOfPlaceItems.filter{it.favou}.forEach {placeItem ->
                items(1) {
                    Box(modifier = Modifier.fillMaxHeight()) {
                        GridItem(placeItem, navController)
                    }
                }
            }
        }
    }
}
