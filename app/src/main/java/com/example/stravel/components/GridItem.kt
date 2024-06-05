package com.example.stravel.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.stravel.model.PlaceItem

@Composable
fun GridItem(it: PlaceItem, navController: NavHostController) {
    val placeId = it.id
    Column(
        modifier = Modifier
            //.fillMaxSize()
            .padding(8.dp)
            .size(width = 180.dp, height = 200.dp)
            .clickable{
                navController.navigate(route = "DetailScreen/$placeId") { // Pass name argument
                }
            }
    ) {
        Image(
            painter = rememberAsyncImagePainter(it.image),
            contentDescription = it.name,
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(18.dp))
                .align(Alignment.CenterHorizontally)
        )
        Text(
            it.name.toString(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        )
    }

}
