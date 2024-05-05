package com.example.stravel.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.stravel.R
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


@Composable
fun AccountContent(mainController: NavController,) {
    var currentUser by remember { mutableStateOf(Firebase.auth.currentUser)}
    var state by remember {
        mutableStateOf(true)
    }
    if (currentUser != null) {
        Column {
            Text(text = "login success")

            val user = Firebase.auth.currentUser
            user?.let {
                // Name, email address, and profile photo Url
                val name = it.displayName
                val email = it.email
                val photoUrl = it.photoUrl

                // Check if user's email is verified
                val emailVerified = it.isEmailVerified

                // The user's ID, unique to the Firebase project. Do NOT use this value to
                // authenticate with your backend server, if you have one. Use
                // FirebaseUser.getIdToken() instead.
                val uid = it.uid
                if (email != null) {
                    Text(text = email)
                }
            }

            Button(
                onClick = {
                    Firebase.auth.signOut()
                    currentUser = null
                    state = !state
                }
            ) {
                Text(text = "Đăng xuất")
            }
        }

    } else {
        NotLoggedContent(mainController)
    }

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
        Image(
            painter = painterResource(id = R.drawable.avatar),
            contentDescription = null,
            modifier = Modifier.size(80.dp)
        )
        Button(onClick = {mainController.navigate("login")}) {
            Text("Login/Register")
        }
    }
}

@Composable
fun LoggedContent(currentUser: FirebaseUser, state: String) {


}