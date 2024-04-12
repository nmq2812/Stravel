package com.example.stravel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.stravel.components.AppNavigation
import com.example.stravel.ui.theme.StravelTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       setContent { 
           Greeting(name = "Stravel")
       }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    AppNavigation()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StravelTheme {
        Greeting("Android")
    }
}