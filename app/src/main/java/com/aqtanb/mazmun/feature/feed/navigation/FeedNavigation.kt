package com.aqtanb.mazmun.feature.feed.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.aqtanb.mazmun.core.domain.model.Screen
import com.aqtanb.mazmun.feature.feed.FeedScreen

fun NavGraphBuilder.feedGraph() {
    composable(route = Screen.NavigationBarScreen.Feed.route) {
        FeedScreen()
    }
}
