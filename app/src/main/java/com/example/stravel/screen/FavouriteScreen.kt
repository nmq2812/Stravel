package com.example.stravel.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.stravel.components.GridItem
import com.example.stravel.components.PlaceItem
import com.example.stravel.components.listOfPlaceItems
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

@Composable
fun FavouriteScreen(navController: NavHostController, listOfPlaceItems: MutableList<PlaceItem>) {
    Box(modifier = Modifier
        .fillMaxSize()
        ,contentAlignment = Alignment.Center
    ) {
        FavouriteContent(navController, listOfPlaceItems)
    }
}

@Composable
fun FavouriteContent(navController: NavHostController, listOfPlaceItems: MutableList<PlaceItem>?) {
    var showDialog by remember { mutableStateOf(false) }
    var updateSurface by remember {
        mutableStateOf(true)
    }

    Scaffold (
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = { showDialog = true }) {
                    Icon(Icons.Default.Add, contentDescription = null)
                }

                if (showDialog) {
                    AlertDialog(
                        onDismissRequest = { showDialog = false },
                        title = { Text(text = "Thêm địa điểm yêu thích") },
                        text = {
                            LazyColumn {
                                item(1) {
                                    ShowPlaceItem()
                                }
                            }
                        },
                        confirmButton = {
                            Button(
                                onClick = {
                                    showDialog = false
                                    updateSurface = !updateSurface
                                },
                                modifier = Modifier.padding(end = 8.dp),
                                content = {
                                    Text(text = "OK")
                                }
                            )
                        },
                        modifier = Modifier.height(400.dp)
                    )
                }

                IconButton(onClick = { navController.navigate(Screens.SettingScreen.name) }) {
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
                updateSurface = !updateSurface
                listOfPlaceItems!!.sortedBy { it.score }
                listOfPlaceItems.filter{it.favou}.forEach {placeItem ->
                        items(1) {
                            Box(modifier = Modifier.fillMaxHeight()) {
                                GridItem(placeItem, navController)
                            }
                        }
                    }
            }
        }
    )

}

@Composable
fun ShowPlaceItem() {
    listOfPlaceItems.forEach { pItem ->
        val checkedState = remember { mutableStateOf(pItem.favou) }
        LazyRow (
            verticalAlignment = Alignment.CenterVertically
        ) {
            item(1) {
                Checkbox(
                    checked = checkedState.value,
                    onCheckedChange = {
                        checkedState.value = it
                        pItem.favou = !pItem.favou
                        listOfPlaceItems.removeAll{it.id == pItem.id}
                        Firebase.database.getReference("PlaceItem").child(pItem.name!!).child("favou").setValue(pItem.favou)
                    }
                )
            }

            item(2) {
                Image(painter = rememberAsyncImagePainter(pItem.image), contentDescription = null )
            }

            item(3) {
                pItem.name?.let { Text(text = it) }
            }
        }
    }
}