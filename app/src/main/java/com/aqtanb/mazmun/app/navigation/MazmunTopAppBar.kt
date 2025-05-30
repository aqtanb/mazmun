package com.aqtanb.mazmun.app.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MazmunTopAppBar(
    appBarTitle: String,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(text = appBarTitle) },
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(Icons.Default.Menu, contentDescription = "Menu")
            }
        },
        actions = {
            IconButton(onClick = { }) {
                Icon(Icons.Default.Search, contentDescription = "Search")
            }
        },
    )
}

@Preview(showSystemUi = true)
@Composable
fun MazmunTopAppBarPreview() {
    MazmunTopAppBar(appBarTitle = "Mazmun")
}
