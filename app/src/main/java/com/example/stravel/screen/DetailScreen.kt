package com.example.stravel.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.example.stravel.components.PlaceItem
import com.example.stravel.components.listOfPlaceItems

@Composable
fun DetailScreen(navController: NavHostController, backStateEntry: NavBackStackEntry) {
    val placeId = backStateEntry.arguments?.getInt("placeId")
    requireNotNull(placeId)
    val pItem: PlaceItem = listOfPlaceItems[placeId.toInt() -1]
    Scaffold(
        topBar = {
            Row() {
                Button(onClick = {
                    navController.navigate("HomeScreen")
                }) {
                    Text("Back")
                }
                Text(
                    placeId.toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }

        },
    ) { paddingValues ->
        Column() {
            Text(
                pItem.name,
                modifier = Modifier
                    .padding(paddingValues)
            )
            Image(
                painter = painterResource(id = pItem.image),
                contentDescription = pItem.name,
                modifier = Modifier
                    .fillMaxWidth()
                    //.size(width = 150.dp, height = 150.dp)
                    .clip(shape = RoundedCornerShape(18.dp))
            )
        }

    }
}