package com.example.stravel.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.example.stravel.R
import com.example.stravel.components.PlaceItem
import com.example.stravel.components.listOfPlaceItems
import com.example.stravel.ui.theme.CardColor

@Composable
fun DetailScreen(navController: NavHostController, backStateEntry: NavBackStackEntry) {
    val placeId = backStateEntry.arguments?.getInt("placeId")
    requireNotNull(placeId)
    val pItem: PlaceItem = listOfPlaceItems[placeId.toInt() -1]
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
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = pItem.name.uppercase(),
                style = TextStyle(fontSize = 36.sp),
                modifier = Modifier
                    .padding(8.dp)
            )
            Image(
                painter = painterResource(id = pItem.image),
                contentDescription = pItem.name,
                modifier = Modifier
                    .fillMaxWidth()
                    //.size(width = 150.dp, height = 150.dp)
                    .clip(shape = RoundedCornerShape(18.dp))
                    .padding(top = 8.dp, bottom = 8.dp)
            )
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 8.dp)
            ) {
                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .size(36.dp)
                        .padding(start = 4.dp, end = 8.dp)
                ) {
                    Icon(painterResource(id = R.drawable.heart), contentDescription = null)
                }
                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .size(36.dp)
                        .padding(start = 4.dp, end = 8.dp)
                ) {
                    Icon(painterResource(id = R.drawable.comment), contentDescription = null)
                }
                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .size(36.dp)
                        .padding(start = 4.dp, end = 8.dp)
                ) {
                    Icon(painterResource(id = R.drawable.send), contentDescription = null)
                }
            }
            Button(
                onClick = {},
                shape = ButtonDefaults.shape,
                colors = ButtonColors(
                    containerColor = CardColor,
                    contentColor = Color.White,
                    disabledContentColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Đặt vé"
                )
            }
            Text(
                text = pItem.description,
                textAlign = TextAlign.Justify
            )
        }

    }
}