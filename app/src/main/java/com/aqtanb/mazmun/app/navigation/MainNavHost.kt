package com.aqtanb.mazmun.app.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aqtanb.mazmun.feature.auth.SignInViewModel
import com.aqtanb.mazmun.feature.feed.FeedScreen
import com.aqtanb.mazmun.feature.home.HomeScreen
import com.aqtanb.mazmun.feature.profile.ProfileScreen

@Composable
fun MainNavHost(
    navController: NavHostController,
    signInViewModel: SignInViewModel
) {
    Scaffold(
        bottomBar = { MazmunNavigationBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.HOME,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Routes.HOME) {
                HomeScreen()
            }

            composable(Routes.FEED) {
                FeedScreen()
            }

            composable(Routes.PROFILE) {
                ProfileScreen(
                    userData = signInViewModel.getCurrentUser(),
                    onSignOut = {
                        signInViewModel.onSignOutClick()
                    }
                )
            }
        }
    }
}