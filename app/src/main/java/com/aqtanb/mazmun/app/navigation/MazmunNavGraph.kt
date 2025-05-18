package com.aqtanb.mazmun.app.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.aqtanb.mazmun.core.domain.model.Screen
import com.aqtanb.mazmun.core.domain.repository.AuthRepository
import com.aqtanb.mazmun.feature.auth.navigation.authGraph
import com.aqtanb.mazmun.feature.feed.navigation.feedGraph
import com.aqtanb.mazmun.feature.profile.navigation.profileGraph

@Composable
fun MazmunNavGraph(authRepository: AuthRepository) {
    val navController = rememberNavController()
    val user by authRepository.currentUser.collectAsState()

    LaunchedEffect(user) {
        if (user != null) {
            navController.navigate("main") {
                popUpTo("auth") { inclusive = true }
            }
        } else {
            navController.navigate("auth") {
                popUpTo("main") { inclusive = true }
            }
        }
    }

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
            authGraph()

            navigation(route = "main", startDestination = Screen.NavigationBarScreen.Feed.route) {
                feedGraph()
                profileGraph()
            }
        }
    }
}
