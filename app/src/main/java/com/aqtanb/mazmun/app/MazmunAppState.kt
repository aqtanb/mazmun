package com.aqtanb.mazmun.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.aqtanb.mazmun.app.navigation.TopLevelDestination
import com.aqtanb.mazmun.core.domain.model.UserData
import com.aqtanb.mazmun.core.domain.repository.AuthRepository
import com.aqtanb.mazmun.feature.channel.navigation.navigateToChannel
import com.aqtanb.mazmun.feature.feed.navigation.navigateToFeed
import com.aqtanb.mazmun.feature.profile.navigation.navigateToProfile
import com.aqtanb.mazmun.feature.search.navigation.navigateToSearch
import kotlinx.coroutines.flow.StateFlow

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
    authRepository: AuthRepository
) {
    val currentUser: StateFlow<UserData?> = authRepository.currentUser

    val isUserAuthenticated: Boolean
        @Composable get() = currentUser.collectAsState().value != null

    private val previousDestination = mutableStateOf<NavDestination?>(null)

    val currentDestination: NavDestination?
        @Composable get() {
            val currentEntry = navController.currentBackStackEntryFlow.collectAsState(initial = null)
            return currentEntry.value?.destination.also { destination ->
                if (destination != null) {
                    previousDestination.value = destination
                }
            } ?: previousDestination.value
        }

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() {
            return TopLevelDestination.entries.firstOrNull { topLevelDestination ->
                currentDestination?.hasRoute(route = topLevelDestination.route) == true
            }
        }

    val shouldShowBottomBar: Boolean
        @Composable get() = currentTopLevelDestination != null

    val shouldShowTopBar: Boolean
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
