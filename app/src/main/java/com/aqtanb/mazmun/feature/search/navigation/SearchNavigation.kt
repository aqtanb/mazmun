package com.aqtanb.mazmun.feature.search.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.aqtanb.mazmun.app.navigation.Screen
import com.aqtanb.mazmun.feature.feed.SearchScreen

fun NavGraphBuilder.searchScreen() {
    composable(Screen.NavigationBarScreen.Search.route) {
        SearchScreen()
    }
}
