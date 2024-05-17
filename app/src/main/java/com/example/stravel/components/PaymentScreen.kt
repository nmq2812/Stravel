package com.example.stravel.components

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp

@Composable
fun PaymentScreen(pItem: PlaceItem, listOfPlaceItems: MutableList<PlaceItem>) {
    val tour: MutableList<PlaceItem> = getRandomElements(listOfPlaceItems, 2, pItem)
    tour.add(pItem)
    var totalCost = pItem.cost
    var enableTour by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .padding(16.dp)
            .scrollable(rememberScrollState(), orientation = Orientation.Vertical)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Đề xuất tour:", modifier = Modifier.padding(16.dp))
            Switch(
                checked = enableTour,
                onCheckedChange = { enableTour = it },
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
        if (enableTour) {
            totalCost = 0
            Column(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                tour.forEach { item ->
                    Card(
                        content = {
                            Text(
                                text = "${item.name}",
                                fontStyle = FontStyle.Italic,
                                textDecoration = TextDecoration.Underline,
                                modifier = Modifier.padding(4.dp)
                            )
                        },
                        colors = CardColors(
                            contentColor = Color.Black,
                            containerColor = Color.Transparent,
                            disabledContentColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent
                        ),
                        modifier = Modifier

                            .padding(4.dp)
                    )
                    totalCost = totalCost!! + item.cost!!
                }
            }
        }


        Text(
            "Giá vé: ${totalCost}",
            modifier = Modifier
                .align(Alignment.End)
                .padding(8.dp)
        )
        // Card number field
        OutlinedTextField(
            value = "", // Replace with actual card number state
            onValueChange = {}, // Replace with actual card number update logic
            label = { Text("Card number") },
            modifier = Modifier.fillMaxWidth()
        )

        // Expiration date field
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedTextField(
                value = "", // Replace with actual expiration date state
                onValueChange = {}, // Replace with actual expiration date update logic
                label = { Text("MM/YY") },
                modifier = Modifier.weight(1f)
            )

            // CVV field
            OutlinedTextField(
                value = "", // Replace with actual CVV state
                onValueChange = {}, // Replace with actual CVV update logic
                label = { Text("CVV") },
                modifier = Modifier.weight(1f)
            )
        }


    }
}

fun getRandomElements(list: MutableList<PlaceItem>, count: Int, pItem: PlaceItem): MutableList<PlaceItem> {
    val filteredList:MutableList<PlaceItem> = list.filter { it != pItem }.toMutableList()
    filteredList.shuffle()


    return filteredList.take(count).toMutableList()
}