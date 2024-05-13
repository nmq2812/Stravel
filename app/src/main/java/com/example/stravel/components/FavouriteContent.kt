package com.example.stravel.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun FavouriteContent(navController: NavHostController, listOfPlaceItems: List<PlaceItem>?) {
    Scaffold (
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Rounded.Settings, contentDescription = null)
                }
                Spacer(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color.Black
                        )
                )
            }
        },
        content = {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.Top,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
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
    )

}
