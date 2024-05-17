package com.example.stravel.screen

import android.annotation.SuppressLint
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.stravel.R
import com.example.stravel.components.PaymentScreen
import com.example.stravel.components.PlaceItem
import com.example.stravel.components.listOfPlaceItems
import com.example.stravel.ui.theme.CardColor
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch


@Composable
fun DetailScreen(
    navController: NavHostController,
    backStateEntry: NavBackStackEntry,
    listOfPlaceItems: MutableList<PlaceItem>
) {
    val placeId = backStateEntry.arguments?.getInt("placeId")
    requireNotNull(placeId)
    val pItem = listOfPlaceItems.find { it.id?.toInt() == placeId }
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
                        Icon(Icons.Default.Share, contentDescription = null)
                    }
                }

            }

        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        if (pItem != null) {
            DetailContent(pItem,paddingValues, listOfPlaceItems)
        }
    }
}


@SuppressLint("MutableCollectionMutableState")
@Composable
fun DetailContent(
    pItem: PlaceItem,
    pValue: PaddingValues,
    listOfPlaceItems: MutableList<PlaceItem>
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    var showDialog by remember { mutableStateOf(false) }
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(pValue),
        state = listState
    ) {
        item(1) {
            pItem.name?.let {
                Text(
                    text = it.uppercase(),
                    style = TextStyle(fontSize = 36.sp),
                    modifier = Modifier
                        .padding(8.dp)

                )
            }
        }
        item(2) {
            Box {
                Image(
                    painter = rememberAsyncImagePainter(pItem.image),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .height(360.dp)
                        .clip(RoundedCornerShape(64.dp))
                )
            }
        }
        item(3) {
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 8.dp)
            ) {
                var isFavou by remember { mutableStateOf(pItem.favou) }

                IconButton(
                    onClick = {
                        isFavou = !isFavou
                        com.example.stravel.components.listOfPlaceItems.removeAll{it.id == pItem.id}
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
                    onClick = {
                        coroutineScope.launch {
                            listState.animateScrollToItem(
                                index = 8,
                                scrollOffset = 1000,
                                //animationSpec = tween(durationMillis = 500)
                            )
                            listState.animateScrollBy(
                                5F,
                                animationSpec = tween(durationMillis = 500)
                            )

                        }
                    },
                    modifier = Modifier
                        .size(36.dp)
                        .padding(start = 4.dp, end = 8.dp)
                ) {
                    Icon(painterResource(id = R.drawable.comment), contentDescription = null)
                }
            }
        }
        item(4) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Giá vé: ${pItem.cost} vnđ",
                    fontStyle = FontStyle.Italic
                )
            }
        }
        item(5) {
            Button(
                onClick = {
                          showDialog = true
                },
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

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = {
                        Text(
                            text = "Đặt vé ${pItem.name}"
                        )
                    },
                    text = {
                        PaymentScreen(pItem, listOfPlaceItems)
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                showDialog = false
                                //updateSurface = !updateSurface
                            },
                            modifier = Modifier.padding(end = 8.dp),
                            content = {
                                Text(text = "Thanh toán")
                            }
                        )
                    },
                    modifier = Modifier
                )
            }
        }
        item(6) {
            HorizontalDivider(
                modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)
            )
            Text(
                text = pItem.description!!,
                textAlign = TextAlign.Justify,
//                style = TextStyle.Default.copy(
//                    lineBreak = LineBreak.Paragraph
//                ),
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
            )
        }
        item(7) {
            Spacer(modifier = Modifier.padding(4.dp))
            ReviewContent(
                pItem,
                pItem.score!!
            ) { newRating ->
                pItem.score = newRating
            }
        }
        item(8) {
            Spacer(modifier = Modifier.padding(16.dp))

            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(24.dp))
            ) {
                val comments by remember {
                    mutableStateOf(
                        mutableListOf(
                            Comment("John Doe", pItem.comments!!.cmt_1),
                            Comment("Quang Trần", pItem.comments!!.cmt_2),
                        )
                    )
                }

                CommentList(comments = comments)
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
        HorizontalDivider(
            modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)
        )
        Text(
            text = "Điểm đánh giá:",
            modifier = Modifier
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clip(RoundedCornerShape(16.dp)),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            for (i in 1..5) {
                item {
                    val starColor = Color.Gray
                    Box (
                        modifier = Modifier
                            .clickable {
                                listOfPlaceItems.removeAll{it.id == pItem.id}
                                starScore = i.toLong()
                                onRatingChange(i.toLong())
                                data?.setValue(starScore)
                            }
                    ){
                        StarIcon(
                            size = 30.dp,
                            color = starColor,
                            filled = starScore >= i
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun StarIcon(size: Dp, color: Color, filled: Boolean,) {
    if (filled) {
        Icon(
            painter = painterResource(id = R.drawable.star),
            contentDescription = "Star",
            tint = color,
            modifier = Modifier
                .size(size)
        )
    }
    else {
        Icon(
            painter = painterResource(id = R.drawable.star_outlined),
            contentDescription = "Star",
            tint = color,
            modifier = Modifier
                .size(size)
        )
    }

}

@Composable
fun CommentList(comments: MutableList<Comment>) {
    var commentText by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column {
        comments.forEach { comment ->
            CommentItem(comment = comment)
        }
    }

    OutlinedTextField(
        value = commentText,
        onValueChange = { commentText = it },
        label = { Text("Thêm bình luận") },
        shape = RoundedCornerShape(16.dp),
        trailingIcon = {
            IconButton(
                onClick = {
                    keyboardController?. hide()
                    if (commentText.isNotEmpty()) {
                        comments.add(Comment("Tôi", text = commentText))
                        println(comments)
                        commentText = ""
                    }
                },
                modifier = Modifier.size(36.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.send),
                    contentDescription = null,
                    tint = CardColor,
                    modifier = Modifier.size(24.dp)
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Send
        ),
        keyboardActions = KeyboardActions(
            onSend = {
                keyboardController?. hide()
                if (commentText.isNotEmpty()) {
                    comments.add(Comment("Tôi", text = commentText))
                    println(comments)
                    commentText = ""
                }
            },
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    )



}

@Composable
fun CommentItem(comment: Comment) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = comment.author, fontWeight = FontWeight.ExtraBold)
            Text(text = comment.text, style = TextStyle.Default.copy(
                lineBreak = LineBreak.Paragraph
            ))
        }
    }
}


data class Comment(val author: String, val text: String)