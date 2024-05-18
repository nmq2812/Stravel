package com.example.stravel.screen

import android.view.Gravity
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.stravel.components.PlaceItem
import com.example.stravel.ui.theme.CardColor

@Composable
fun PaymentScreen(navController: NavHostController, backStateEntry: NavBackStackEntry, listOfPlaceItems: MutableList<PlaceItem>) {
    val context  = LocalContext.current
    val placeId = backStateEntry.arguments?.getInt("placeId")
    requireNotNull(placeId)
    val pItem: PlaceItem = listOfPlaceItems.find { it.id?.toInt() == placeId }!!
    val tour: MutableList<PlaceItem> = getRandomElements(listOfPlaceItems, 2, pItem)
    tour.add(pItem)
    var totalCost = pItem.cost
    var enableTour by remember {
        mutableStateOf(false)
    }
    var cardNumValue by remember {
        mutableStateOf("")
    }
    var dateValue by remember {
        mutableStateOf("")
    }
    var cvvValue by remember {
        mutableStateOf("")
    }

    Scaffold (
        topBar = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                }
            }
        },
        content = {
            LazyColumn(
                modifier = Modifier
                    .padding(it)
                    .scrollable(rememberScrollState(), orientation = Orientation.Vertical),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    pItem.name?.let {
                        Text(
                            text = it.uppercase(),
                            style = TextStyle(fontSize = 40.sp),
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth()
                        )
                    }
                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(pItem.image),
                            contentDescription = null,
                            modifier = Modifier
                                .size(128.dp)
                                .clip(RoundedCornerShape(16.dp))
                        )
                    }
                }

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
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
                            var index = 1
                            tour.forEach { item ->
                                Card(
                                    content = {
                                        Row (
                                            modifier = Modifier.fillMaxWidth(),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Box(
                                                modifier = Modifier
                                                    .size(24.dp)
                                                    .background(
                                                        color = CardColor,
                                                        shape = CircleShape
                                                    ),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text(
                                                    index.toString(),
                                                    color = Color.White
                                                )
                                            }
                                            Text(
                                                text = "${item.name}",
                                                fontStyle = FontStyle.Italic,
                                                textDecoration = TextDecoration.Underline,
                                                modifier = Modifier.padding(4.dp)
                                            )
                                        }
                                    },
                                    colors = CardColors(
                                        contentColor = Color.Black,
                                        containerColor = Color.Transparent,
                                        disabledContentColor = Color.Transparent,
                                        disabledContainerColor = Color.Transparent
                                    ),
                                    modifier = Modifier
                                )
                                totalCost = totalCost!! + item.cost!!
                                if (index <3) {
                                    Text(text = "  |", modifier = Modifier.fillMaxWidth())
                                }
                                index += 1
                            }
                        }
                    }

                }

                item {
                    Row (
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ){
                        Text(
                            "Giá vé: ${totalCost}",
                            modifier = Modifier
                                .padding(8.dp)
                        )
                    }

                }

                item {
                    OutlinedTextField(
                        value = cardNumValue, // Replace with actual card number state
                        onValueChange = {cardNumValue = it}, // Replace with actual card number update logic
                        label = { Text("Card number") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Expiration date field
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        OutlinedTextField(
                            value = dateValue, // Replace with actual expiration date state
                            onValueChange = {dateValue = it}, // Replace with actual expiration date update logic
                            label = { Text("MM/YY") },
                            modifier = Modifier.weight(1f)
                        )

                        // CVV field
                        OutlinedTextField(
                            value = cvvValue, // Replace with actual CVV state
                            onValueChange = {cvvValue = it}, // Replace with actual CVV update logic
                            label = { Text("CVV") },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                item {
                    Button(
                        onClick = {
                            if (cardNumValue == "" || dateValue == "" || cvvValue == "") {
                                val toast = Toast.makeText(context,
                                    "Thông tin thẻ không đủ!",
                                    Toast.LENGTH_SHORT)
                                toast.setGravity(Gravity.TOP, 0, 0)
                                toast.show()
                            } else {
                                val toast = Toast.makeText(context,
                                    "Thông tin thẻ không đủ!",
                                    Toast.LENGTH_SHORT)
                                toast.setGravity(Gravity.TOP, 0, 0)
                                toast.show()
                                navController.navigateUp()
                            }
                        },

                        modifier = Modifier
                            .padding(start = 8.dp, end = 8.dp)
                            .fillMaxWidth(),
                        content = {
                            Text(text = "Thanh toán")
                        }
                    )
                }


            }
        }

    )



}