package com.aqtanb.mazmun.app.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.aqtanb.mazmun.core.domain.repository.AuthRepository
import com.aqtanb.mazmun.feature.auth.AuthScreen
import com.aqtanb.mazmun.feature.auth.SignInViewModel
import com.aqtanb.mazmun.feature.feed.FeedScreen
import com.aqtanb.mazmun.feature.profile.ProfileScreen
import com.aqtanb.mazmun.feature.profile.ProfileViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MazmunNavGraph(
    authRepository: AuthRepository
) {
    val navController = rememberNavController()
    val user by authRepository.currentUser.collectAsState()

    Scaffold(
        bottomBar = {
            val route = navController.currentBackStackEntryAsState().value?.destination?.route
            if (route == "main/feed" || route == "main/profile") BottomBar(navController)
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = if (user != null) "main/feed" else "auth/signin",
            modifier = Modifier.padding(padding)
        ) {
            composable("auth/signin") {
                val vm: SignInViewModel = koinViewModel()
                AuthScreen(
                    authUiState = vm.authUiState.collectAsStateWithLifecycle().value,
                    onSignInClick = vm::onSignInClick
                )
            }
            composable("main/feed") {
                FeedScreen()
            }
            composable("main/profile") {
                val vm: ProfileViewModel = koinViewModel()
                ProfileScreen(
                    userData = user,
                    onSignOut = {
                        vm.onSignOut()
                        navController.navigate("auth/signin") {
                            popUpTo("main") { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}