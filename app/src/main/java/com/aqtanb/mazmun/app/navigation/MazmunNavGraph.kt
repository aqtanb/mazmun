package com.aqtanb.mazmun.app.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.aqtanb.mazmun.core.model.UserData
import com.aqtanb.mazmun.feature.auth.navigation.authScreen
import com.aqtanb.mazmun.feature.channel.navigation.channelScreen
import com.aqtanb.mazmun.feature.feed.navigation.feedScreen
import com.aqtanb.mazmun.feature.profile.navigation.profileScreen
import com.aqtanb.mazmun.feature.search.navigation.searchScreen

@Composable
fun MazmunNavGraph(
    user: UserData?,
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = {
            val route = navController.currentBackStackEntryAsState().value?.destination?.route
            if (route?.startsWith("main") == true) MazmunNavigationBar(navController)
        },
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = if (user != null) "main" else "auth",
            modifier = Modifier.padding(padding),
        ) {
            authScreen()
            navigation(route = "main", startDestination = Screen.NavigationBarScreen.Feed.route) {
                channelScreen()
                searchScreen()
                feedScreen()
                profileScreen(onSignOut = {
                    navController.navigate("auth") {
                        popUpTo("main") { inclusive = true }
                    }
                })
            }
        }
    }
}
