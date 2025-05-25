package com.aqtanb.mazmun.app.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MazmunTopAppBar(
    appBarTitle: String,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        modifier = modifier.padding(horizontal = 12.dp),
        title = { Text(text = appBarTitle) },
    )
}

@Preview(showSystemUi = true)
@Composable
fun MazmunTopAppBarPreview() {
    MazmunTopAppBar(appBarTitle = "Mazmun")
}
