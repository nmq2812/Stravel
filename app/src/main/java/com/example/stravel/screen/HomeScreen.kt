package com.example.stravel.screen

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.stravel.components.GridItem
import com.example.stravel.components.SearchBar
import com.example.stravel.model.PlaceItem
import com.example.stravel.ui.theme.CardColor
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

@Composable
fun HomeScreen(navController: NavHostController, listOfPlaceItems: MutableList<PlaceItem>) {
    var searchBarValue by remember { mutableStateOf("") }
    val configuration = LocalConfiguration.current
    val orientation = when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> "Landscape"
        Configuration.ORIENTATION_PORTRAIT -> "Portrait"
        else -> "Undefined"
    }

    listOfPlaceItems.distinctBy { it.name }
    Box(modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column() {
                var appName by remember { mutableStateOf("") }
                val database = Firebase.database.getReference("AppName")
                val dataListener = object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        appName = snapshot.getValue<String>().toString()
                    }

                    override fun onCancelled(error: DatabaseError) {

                    }
                }
                database.addValueEventListener(dataListener)

                Text(
                    appName.uppercase(),
                    style = TextStyle(fontSize = 48.sp),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(8.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(modifier = Modifier.fillMaxWidth()) {
                SearchBar(
                    value = searchBarValue, // Pass the searchBarValue directly
                    onSearch = { newValue -> searchBarValue = newValue }
                )
            }

            Column(modifier = Modifier.fillMaxSize()) {
                HomeContent(navController, searchBarValue, listOfPlaceItems)
            }
        }
    }
}

@Composable
fun HomeContent(
    navController: NavHostController,
    searchValue: String,
    listOfPlaceItems: MutableList<PlaceItem>
) {

    var pItems by remember {
        mutableStateOf(listOfPlaceItems)
    }
    var filledCost by remember { mutableStateOf(100000f) }
    var expandedFilter by remember { mutableStateOf(false) }
    var expandedSort by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.End
    ) {
        Column {
            Row {
                Text(
                    text = "Lọc",
                    color = CardColor,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .clickable {
                            expandedFilter = true
                        }
                )
                if (!expandedFilter) {
                    Icon(
                        Icons.Filled.KeyboardArrowDown,
                        contentDescription = null,
                        tint = CardColor
                    )
                } else {
                    Icon(
                        Icons.Filled.KeyboardArrowUp,
                        contentDescription = null,
                        tint = CardColor
                    )
                }

            }
            DropdownMenu(
                expanded = expandedFilter,
                onDismissRequest = {
                    expandedFilter = false
                },
                modifier = Modifier.width(200.dp)
            ) {
                DropdownMenuItem(
                    enabled = false,
                    text = {
                        Column() {
                            Text("Theo giá:", modifier = Modifier.padding(4.dp))
                            Slider(
                                value = filledCost,
                                onValueChange = { newValue ->
                                    filledCost = newValue
                                },
                                valueRange = 0f..100000f,
                                steps = 1000,
                                colors = SliderDefaults.colors(
                                    thumbColor = CardColor,
                                    activeTrackColor = CardColor
                                ),
                                modifier = Modifier.height(24.dp)
                            )
                            Text("${filledCost.toInt()} vnđ")
                        }
                    },
                    onClick = {  }
                )
            }
        }

        Spacer(modifier = Modifier.padding(4.dp))

        Column {
            Row {
                Text(
                    text = "Sắp xếp",
                    color = CardColor,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .clickable {
                            expandedSort = true
                        }
                )
                if (!expandedSort) {
                    Icon(
                        Icons.Filled.KeyboardArrowDown,
                        contentDescription = null,
                        tint = CardColor
                    )
                } else {
                    Icon(
                        Icons.Filled.KeyboardArrowUp,
                        contentDescription = null,
                        tint = CardColor
                    )
                }
            }
            DropdownMenu(
                expanded = expandedSort,
                onDismissRequest = {
                    expandedSort = false
                }
            ) {
                DropdownMenuItem(
                    text = {
                        Text("Tên: A -> Z")
                    },
                    onClick = {
                        expandedSort= false
                        pItems = pItems.sortedBy { it.name }.toMutableList()
                    }
                )
                DropdownMenuItem(
                    text = {
                        Text("Tên: Z -> A")
                    },
                    onClick = {
                        expandedSort = false
                        pItems = pItems.sortedBy { it.name }.toMutableList()
                        pItems.reverse()
                    }
                )
                DropdownMenuItem(
                    text = {
                        Text("Đánh giá: Cao -> Thấp")
                    },
                    onClick = {
                        expandedSort = false
                        pItems = pItems.sortedBy { it.avgScore }.toMutableList()
                        pItems.reverse()
                    }
                )
                DropdownMenuItem(
                    text = {
                        Text("Đánh giá: Thấp -> Cao")
                    },
                    onClick = {
                        expandedSort = false
                        pItems = pItems.sortedBy { it.avgScore }.toMutableList()
                    }
                )
                DropdownMenuItem(
                    text = {
                        Text("Giá: Cao -> Thấp")
                    },
                    onClick = {
                        expandedSort = false
                        pItems = pItems.sortedBy { it.cost }.toMutableList()
                        pItems.reverse()
                    }
                )
                DropdownMenuItem(
                    text = {
                        Text("Giá: Thấp -> Cao")
                    },
                    onClick = {
                        expandedSort = false
                        pItems = pItems.sortedBy { it.cost }.toMutableList()
                    }
                )
            }
        }
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize()
    ) {
        pItems.forEach {placeItem ->
            if (searchValue == "" || placeItem.name!!.lowercase().contains(searchValue.lowercase())) {
                if (placeItem.cost!! <= filledCost.toInt()) {
                    items(1) {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            GridItem(placeItem, navController)
                        }
                    }
                }

            }
        }
    }
}
