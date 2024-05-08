package com.example.stravel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stravel.components.AppNavigation
import com.example.stravel.components.PlaceItem
import com.example.stravel.screen.LoginScreen
import com.example.stravel.screen.RegisterScreen
import com.example.stravel.ui.theme.StravelTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

private lateinit var auth: FirebaseAuth
private lateinit var placeItemReference: DatabaseReference
private var listOfPlaceItems: MutableList<PlaceItem> = mutableListOf()
// ...
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            auth = Firebase.auth
            placeItemReference = Firebase.database.reference
           StravelTheme {
               Greeting()
           }
       }
    }
}



@Composable
fun GetPlaceItemList() {
    var database = Firebase.database.getReference("PlaceItem")

    val dataListener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            for (pItemSnapshot in snapshot.children) {
                val pItem = pItemSnapshot.getValue<PlaceItem>()
                if (pItem != null) {
                    listOfPlaceItems.add(pItem)
                }
            }
        }

        override fun onCancelled(error: DatabaseError) {
        }
    }
    database.addValueEventListener(dataListener)

}

@Composable
fun Greeting() {
    val mainController = rememberNavController()
    NavHost(mainController,"main") {
        composable("login") { LoginScreen(mainController, auth) }
        composable("register") { RegisterScreen(mainController, auth) }
        composable("main") {AppNavigation(mainController, listOfPlaceItems)}
    }
    GetPlaceItemList()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StravelTheme {
        Greeting()
    }
}