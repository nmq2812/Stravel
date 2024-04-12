package com.example.stravel.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.stravel.R

@Composable
fun NavBar(modifier: Modifier = Modifier) {
    var homeIcon by remember {
        mutableStateOf(R.drawable.home_24_outline)
    }
    var favouriteIcon by remember {
        mutableStateOf(R.drawable.home_24_outline)
    }
    var settingIcon by remember {
        mutableStateOf(R.drawable.home_24_outline)
    }

    var homeSelected by remember {
        mutableStateOf(true)
    }

    var favouriteSelected by remember {
        mutableStateOf(false)
    }

    var settingSelected by remember {
        mutableStateOf(false)
    }
    Scaffold(
        bottomBar = {
            BottomAppBar(modifier = Modifier.fillMaxWidth()) {
                // Add your navigation items here
                NavigationBarItem(
                    selected = homeSelected,
                    onClick = {
                        if (homeSelected) homeIcon = R.drawable.home_24
                        else homeIcon = R.drawable.home_24_outline;
                        favouriteSelected = false;
                        settingSelected = false
                              },
                    icon = { Icon(painter = painterResource(id = homeIcon), contentDescription = null)
                    }
                )
                NavigationBarItem(
                    selected = favouriteSelected,
                    onClick = {
                        if (favouriteSelected) favouriteIcon = R.drawable.home_24
                        else favouriteIcon = R.drawable.home_24_outline;
                        homeSelected = false;
                        settingSelected = false
                    },
                    icon = { Icon(painter = painterResource(id = homeIcon), contentDescription = null)
                    }
                )
                NavigationBarItem(
                    selected = homeSelected,
                    onClick = {
                        if (settingSelected) settingIcon = R.drawable.home_24
                        else settingIcon = R.drawable.home_24_outline;
                        favouriteSelected = false;
                        homeSelected = false
                    },
                    icon = { Icon(painter = painterResource(id = homeIcon), contentDescription = null)
                    }
                )
            }
        }
    ) {}
}