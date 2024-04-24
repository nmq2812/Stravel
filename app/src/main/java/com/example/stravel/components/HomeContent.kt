package com.example.stravel.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HomeContent() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.End
    ) {
        Text(text = "filter")
        Text(text = "sort")
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize()
    ) {

        listOfPlaceItems.forEach {placeItem ->
            items(1) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    GridItem(placeItem)
                }
            }
        }
    }
}


@Composable
fun GridItem(it: PlaceItem) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = it.image),
            contentDescription = it.name,
            modifier = Modifier
                .fillMaxSize()
                .size(width = 170.dp, height = 100.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            it.name,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }

}



@Preview(showBackground = true)
@Composable
fun HomePreview() {
    HomeContent()
}