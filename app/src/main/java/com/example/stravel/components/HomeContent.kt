package com.example.stravel.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.stravel.ui.theme.CardColor

@Composable
fun HomeContent(
    navController: NavHostController,
    searchValue: String,
    listOfPlaceItems: List<PlaceItem>
) {
    var reloadContent by remember {mutableStateOf(true)}
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = "filter",
            color = CardColor,
            modifier = Modifier
                .clickable {

                }
        )
        Spacer(modifier = Modifier.padding(4.dp))
        Text(
            text = "sort",
            color = CardColor,
            modifier = Modifier
                .clickable {

                }
        )
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize()
    ) {

        listOfPlaceItems.forEach {placeItem ->
            listOfPlaceItems.distinctBy { it.id }
            if (searchValue == "" || placeItem.name!!.lowercase().contains(searchValue.lowercase())) {
                items(1) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        GridItem(placeItem, navController)
                    }
                }
            }
        }
    }
}
