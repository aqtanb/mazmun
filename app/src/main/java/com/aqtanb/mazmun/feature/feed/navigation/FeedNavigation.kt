package com.aqtanb.mazmun.feature.feed.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.aqtanb.mazmun.feature.feed.FeedScreen
import kotlinx.serialization.Serializable

@Serializable data object FeedRoute

fun NavController.navigateToFeed(navOptions: NavOptions? = null) {
    navigate(route = FeedRoute, navOptions = navOptions)
}

fun NavGraphBuilder.feedScreen(
    onNavigateToProfile: (String) -> Unit,
    onNavigateToChannel: (String) -> Unit,
    onGoToSearch: () -> Unit
) {
    composable<FeedRoute> {
        FeedScreen(
            onGoToSearch = onGoToSearch,
        )
    }
}
