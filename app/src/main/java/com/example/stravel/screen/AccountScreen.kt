package com.example.stravel.screen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.stravel.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


@Composable
fun AccountScreen(mainController: NavController, navController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        AccountContent(mainController, navController)
    }

}

@Composable
fun AccountContent(mainController: NavController, navController: NavHostController) {
    var currentUser by remember { mutableStateOf(Firebase.auth.currentUser) }
    val imageValue = rememberSaveable {
        mutableStateOf("")
    }
    val painter = rememberImagePainter(
        if (imageValue.value.isEmpty()) {
            R.drawable.avatar
        } else {
            imageValue.value
        }
    )
    var state by remember {
        mutableStateOf(true)
    }
    val laucher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri.let {imageValue.value = it.toString()}
    }
    Scaffold (
        containerColor = Color.Transparent,
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
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
            if (currentUser != null) {
                LazyColumn(
                    modifier = Modifier
                        .padding(it)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item (1) {
                        Image(
                            painter = painter,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(16.dp)
                                .size(128.dp)
                                .clickable { laucher.launch("image/*") }
                                .clip(shape = CircleShape)
                        )
                    }

                    item (2) {
                        val user = Firebase.auth.currentUser
                        user?.let {
                            // Name, email address, and profile photo Url
                            val email = it.email
                            val uid = it.uid

                            ElevatedCard (
                                modifier = Modifier.padding(4.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp)
                                ) {
                                    Text(
                                        "UID: ",
                                        fontSize = 12.sp
                                    )
                                    Text(
                                        uid,
                                        fontSize = 12.sp
                                    )
                                }
                            }

                            ElevatedCard(
                                modifier = Modifier.padding(4.dp)

                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp)
                                ) {
                                    Text(
                                        text = "Email: ",
                                        fontSize = 12.sp
                                    )
                                    if (email != null) {
                                        Text(
                                            text = email,
                                            fontSize = 12.sp
                                        )
                                    }
                                }
                            }
                        }
                    }

                    item(3) {
                        ElevatedCard(
                            modifier = Modifier.padding(4.dp)

                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                                    .clickable { navController.navigate((Screens.FavouriteScreen.name)) }
                            ) {
                                Text(
                                    "Danh sách yêu thích: ",
                                    fontSize = 12.sp
                                )
                                Icon(
                                    Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                    contentDescription = null
                                )
                            }
                        }
                    }

                    item (4) {
                        Button(
                            onClick = {
                                Firebase.auth.signOut()
                                currentUser = null
                                state = !state
                            },
                            modifier = Modifier.padding(it)
                        ) {
                            Text(text = "Đăng xuất")
                        }
                    }
                }

            } else {
                NotLoggedContent(mainController)
            }

        }
    )

}

@Composable
fun NotLoggedContent(mainController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Bạn chưa đăng nhập!",
            fontWeight = FontWeight.ExtraBold
        )
        Button(onClick = {mainController.navigate("login")}) {
            Text("Login/Register")
        }
    }
}
