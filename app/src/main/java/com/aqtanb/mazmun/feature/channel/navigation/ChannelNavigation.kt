package com.aqtanb.mazmun.feature.channel.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.aqtanb.mazmun.feature.channel.ChannelDetailScreen
import com.aqtanb.mazmun.feature.channel.ChannelScreen
import kotlinx.serialization.Serializable

@Serializable data object ChannelRoute

@Serializable data class ChannelDetailRoute(val channelId: String)

fun NavController.navigateToChannel(navOptions: NavOptions? = null) {
    navigate(route = ChannelRoute, navOptions = navOptions)
}

fun NavController.navigateToChannelDetail(
    channelId: String,
    navOptions: NavOptions? = null
) {
    navigate(route = ChannelDetailRoute(channelId), navOptions = navOptions)
}

fun NavGraphBuilder.channelScreen(onNavigateToChannelDetail: (String) -> Unit) {
    composable<ChannelRoute> {
        ChannelScreen()
    }

    composable<ChannelDetailRoute> { backStackEntry ->
        val route = backStackEntry.toRoute<ChannelDetailRoute>()
        ChannelDetailScreen(
            channelId = route.channelId,
        )
    }
}
