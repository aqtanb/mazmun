package com.aqtanb.mazmun.feature.auth.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.aqtanb.mazmun.core.domain.model.UserData
import com.aqtanb.mazmun.feature.auth.AuthScreen
import com.aqtanb.mazmun.feature.auth.SignInViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Serializable data object SignInRoute

fun NavController.navigateToAuth(navOptions: NavOptions? = null) {
    navigate(route = SignInRoute, navOptions = navOptions)
}

fun NavGraphBuilder.authScreen(currentUser: StateFlow<UserData?>) {
    composable<SignInRoute> {
        val signInViewModel: SignInViewModel = koinViewModel {
            parametersOf(currentUser)
        }
        val authUiState by signInViewModel.authUiState.collectAsStateWithLifecycle()

        AuthScreen(
            authUiState = authUiState,
            onSignInClick = signInViewModel::onSignInClick,
        )
    }
}
