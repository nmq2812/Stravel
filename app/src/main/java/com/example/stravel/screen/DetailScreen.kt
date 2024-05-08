package com.example.stravel.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.example.stravel.R
import com.example.stravel.components.DetailContent
import com.example.stravel.components.PlaceItem


@Composable
fun DetailScreen(
    navController: NavHostController,
    backStateEntry: NavBackStackEntry,
    listOfPlaceItems: MutableList<PlaceItem>?
) {
    val placeId = backStateEntry.arguments?.getInt("placeId")
    requireNotNull(placeId)
    val pItem = listOfPlaceItems?.get(placeId.toInt() -1)
    Scaffold(
        topBar = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = {navController.navigateUp()}) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                }
                Row(
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(onClick = {}) {
                        Icon(painterResource(id = R.drawable.heart_24_outline), contentDescription = null)
                    }
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Share, contentDescription = null)
                    }
                }

            }

        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        if (pItem != null) {
            DetailContent(pItem, listOfPlaceItems, paddingValues)
        }
    }
}