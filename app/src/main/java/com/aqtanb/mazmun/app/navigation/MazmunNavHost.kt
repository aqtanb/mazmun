package com.aqtanb.mazmun.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.navigation
import com.aqtanb.mazmun.app.MazmunAppState
import com.aqtanb.mazmun.feature.auth.navigation.SignInRoute
import com.aqtanb.mazmun.feature.auth.navigation.authScreen
import com.aqtanb.mazmun.feature.channel.navigation.channelScreen
import com.aqtanb.mazmun.feature.channel.navigation.navigateToChannelDetail
import com.aqtanb.mazmun.feature.feed.navigation.FeedRoute
import com.aqtanb.mazmun.feature.feed.navigation.feedScreen
import com.aqtanb.mazmun.feature.profile.navigation.navigateToUserProfile
import com.aqtanb.mazmun.feature.profile.navigation.profileScreen
import com.aqtanb.mazmun.feature.search.navigation.searchScreen
import kotlinx.serialization.Serializable

@Serializable data object MainRoute

@Serializable data object AuthRoute

@Composable
fun MazmunNavHost(
    appState: MazmunAppState,
    modifier: Modifier = Modifier
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = if (appState.isUserAuthenticated) MainRoute else AuthRoute,
        modifier = modifier,
    ) {
        navigation<AuthRoute>(startDestination = SignInRoute::class) {
            authScreen(
                currentUser = appState.currentUser,
            )
        }

        navigation<MainRoute>(startDestination = FeedRoute::class) {
            feedScreen(
                onNavigateToProfile = { userId ->
                    navController::navigateToUserProfile
                },
                onNavigateToChannel = { channelId ->
                    navController::navigateToChannelDetail
                },
                onGoToSearch = {
                    appState.navigateToTopLevelDestination(TopLevelDestination.SEARCH)
                },
            )

            searchScreen(
                onNavigateToProfile = { userId ->
                    navController.navigateToUserProfile(userId)
                },
                onNavigateToChannel = { channelId ->
                    navController.navigateToChannelDetail(channelId)
                },
            )

            channelScreen(
                onNavigateToChannelDetail = { channelId ->
                    navController.navigateToChannelDetail(channelId)
                },
            )

            profileScreen(
                onSignOut = {
                    navController.navigate(SignInRoute) {
                        popUpTo(MainRoute) { inclusive = true }
                    }
                },
                userData = appState.currentUser.value,
            )
        }
    }
}
