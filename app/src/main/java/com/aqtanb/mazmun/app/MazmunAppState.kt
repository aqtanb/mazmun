package com.aqtanb.mazmun.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.aqtanb.mazmun.app.navigation.TopLevelDestination
import com.aqtanb.mazmun.core.domain.repository.AuthRepository
import com.aqtanb.mazmun.feature.channel.navigation.ChannelRoute
import com.aqtanb.mazmun.feature.channel.navigation.navigateToChannel
import com.aqtanb.mazmun.feature.feed.navigation.FeedRoute
import com.aqtanb.mazmun.feature.feed.navigation.navigateToFeed
import com.aqtanb.mazmun.feature.profile.navigation.ProfileRoute
import com.aqtanb.mazmun.feature.profile.navigation.navigateToProfile
import com.aqtanb.mazmun.feature.search.navigation.SearchRoute
import com.aqtanb.mazmun.feature.search.navigation.navigateToSearch

@Composable
fun rememberMazmunAppState(
    navController: NavHostController = rememberNavController(),
    authRepository: AuthRepository
): MazmunAppState {
    return remember(navController, authRepository) {
        MazmunAppState(navController, authRepository)
    }
}

@Stable
class MazmunAppState(
    val navController: NavHostController,
    private val authRepository: AuthRepository
) {
    val isUserAuthenticated: Boolean
        @Composable get() = authRepository.currentUser
            .collectAsState().value != null
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            FeedRoute::class.qualifiedName -> TopLevelDestination.FEED
            SearchRoute::class.qualifiedName -> TopLevelDestination.SEARCH
            ChannelRoute::class.qualifiedName -> TopLevelDestination.CHANNEL
            ProfileRoute::class.qualifiedName -> TopLevelDestination.PROFILE
            else -> null
        }

    val shouldShowBottomBar: Boolean
        @Composable get() = currentTopLevelDestination != null

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (topLevelDestination) {
            TopLevelDestination.FEED -> navController.navigateToFeed(topLevelNavOptions)
            TopLevelDestination.SEARCH -> navController.navigateToSearch(topLevelNavOptions)
            TopLevelDestination.CHANNEL -> navController.navigateToChannel(topLevelNavOptions)
            TopLevelDestination.PROFILE -> navController.navigateToProfile(topLevelNavOptions)
        }
    }
}
