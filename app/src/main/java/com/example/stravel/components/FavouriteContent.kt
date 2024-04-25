package com.example.stravel.components

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable

@Composable
fun FavouriteContent() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2)
    ) {
        items(2) {

        }
    }
}