package com.aqtanb.mazmun.app.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBar(navController: NavHostController) {
    BottomAppBar {
        NavigationBarItem(
            selected = navController.currentBackStackEntryAsState().value?.destination?.route == "main/feed",
            icon = { Icon(Icons.Default.Home, null) },
            label = { Text("Feed") },
            onClick = { navController.navigate("main/feed") },
        )
        NavigationBarItem(
            selected = navController.currentBackStackEntryAsState().value?.destination?.route == "main/profile",
            icon = { Icon(Icons.Default.Person, null) },
            label = { Text("Profile") },
            onClick = { navController.navigate("main/profile") },
        )
    }
}
