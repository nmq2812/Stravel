package com.example.stravel.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun SettingScreen(navController: NavHostController) {
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
            }
        },
        content = {
            Column (
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(it)
            ) {
                SettingsList()
            }
        }
    )

}

@Composable
fun SettingsList() {
    var notificationsEnabled by remember {
        mutableStateOf(true)
    }
    var notificationFrequency by remember {
        mutableStateOf(0f)
    }
    var themeExpanded by remember {
        mutableStateOf(false)
    }
    var crashReportingEnabled by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "General Settings: ",
            modifier = Modifier.padding(16.dp)
        )

        Switch(
            checked = notificationsEnabled,
            onCheckedChange = { notificationsEnabled = it },
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Text(
            text = "Notification Frequency",
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Slider(
            value = notificationFrequency,
            onValueChange = { notificationFrequency = it },
            valueRange = 1f..10f,
            steps = 9,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Text(
            text = "Advanced Settings:",
            modifier = Modifier.padding(16.dp)
        )

        Switch(
            checked = crashReportingEnabled,
            onCheckedChange = { crashReportingEnabled = it },
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Text(
            text = "Theme",
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Button(
            onClick = { themeExpanded = true },
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            Text(text = "light/dark theme")
        }

        DropdownMenu(
            expanded = themeExpanded,
            onDismissRequest = { themeExpanded = false }
        ) {
            DropdownMenuItem(
                onClick = {
                    themeExpanded = false
                },
                text = {
                    Text(text = "Light")
                }
            )

            DropdownMenuItem(
                onClick = {
                    themeExpanded = false
                },
                text = {
                    Text(text = "Dark")
                }
            )
        }
    }
}