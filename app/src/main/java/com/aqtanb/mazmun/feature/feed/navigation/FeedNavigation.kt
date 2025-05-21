package com.aqtanb.mazmun.feature.feed.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.aqtanb.mazmun.app.navigation.Screen
import com.aqtanb.mazmun.feature.feed.FeedScreen

fun NavGraphBuilder.feedScreen() {
    composable(route = Screen.NavigationBarScreen.Feed.route) {
        FeedScreen()
    }
}
