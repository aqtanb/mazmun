package com.aqtanb.mazmun.feature.auth.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.aqtanb.mazmun.core.domain.model.Screen
import com.aqtanb.mazmun.feature.auth.AuthScreen
import com.aqtanb.mazmun.feature.auth.SignInViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.authGraph() {
    navigation(
        route = "auth",
        startDestination = Screen.SignIn.route,
    ) {
        composable(Screen.SignIn.route) {
            val signInViewModel: SignInViewModel = koinViewModel()
            val authUiState by signInViewModel.authUiState.collectAsStateWithLifecycle()
            AuthScreen(
                authUiState = authUiState,
                onSignInClick = {
                    signInViewModel.onSignInClick()
                },
            )
        }
    }
}
