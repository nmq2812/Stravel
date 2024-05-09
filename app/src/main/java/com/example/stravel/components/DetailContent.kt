package com.example.stravel.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.stravel.R
import com.example.stravel.ui.theme.CardColor
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

@Composable
fun DetailContent(pItem: PlaceItem, pValue: PaddingValues) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(pValue)
    ) {
        item {
            pItem.name?.let {
                Text(
                    text = it.uppercase(),
                    style = TextStyle(fontSize = 36.sp),
                    modifier = Modifier
                        .padding(8.dp)

                )
            }
        }
        item {
            Box {
                Image(
                    painter = rememberAsyncImagePainter(pItem.image),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .height(360.dp)
                )
            }
        }
        item {
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 8.dp)
            ) {
                var isFavou by remember {mutableStateOf(pItem.favou)}

                IconButton(
                    onClick = {
                        isFavou = !isFavou
                        listOfPlaceItems.remove(pItem)
                        Firebase.database.getReference("PlaceItem").child(pItem.name!!).child("favou").setValue(isFavou)
                    },
                    modifier = Modifier
                        .size(36.dp)
                        .padding(start = 4.dp, end = 8.dp)
                ) {
                    if (isFavou) {
                        Icon(
                            painterResource(id = R.drawable.heart),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.error
                        )
                    } else {
                        Icon(
                            painterResource(id = R.drawable.heart_outline),
                            contentDescription = null
                        )
                    }

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
        }
        item {
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
        }
        item {
            Text(
                text = pItem.description!!,
                textAlign = TextAlign.Justify
            )
        }
        item {
            Spacer(modifier = Modifier.padding(4.dp))
            ReviewContent(
                pItem,
                pItem.score!!
            ) { newRating ->
                pItem.score = newRating
            }
        }
    }
}

@Composable
fun ReviewContent(
    pItem: PlaceItem,
    score: Long,
    onRatingChange: (Long) -> Unit
) {
    var starScore by remember { mutableLongStateOf(score) }
    val data = pItem.name?.let { Firebase.database.getReference("PlaceItem").child(it).child("score") }
    val dataListener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
           starScore = snapshot.value as Long
        }

        override fun onCancelled(error: DatabaseError) {

        }
    }
    data?.addValueEventListener(dataListener)
    val item = "-null-"
    if (score > 5 || score < 0) {
        Text(
            text = item,
            color = Color.Red,
            fontStyle = FontStyle.Italic
        )
    } else {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            for (i in 1..5) {
                item {
                    val starColor = if (starScore >= i) Color.Yellow else Color.Gray
                    Box (
                        modifier = Modifier
                            .clickable {
                                starScore = i.toLong()
                                onRatingChange(i.toLong())
                                data?.setValue(starScore)
                            }
                    ){
                        StarIcon(
                            size = 54.dp,
                            color = starColor,
                            filled = score >= i
                        )
                    }
                }
            }
        }
        Text(
            text = "Rating: $starScore/5",
            modifier = Modifier
        )
    }
}

@Composable
fun StarIcon(size: Dp, color: Color, filled: Boolean,) {
    Icon(
        imageVector = if (filled) {Icons.Filled.Star}
        else {Icons.Outlined.Star},
        contentDescription = "Star",
        tint = color,
        modifier = Modifier
            .size(size)
    )
}