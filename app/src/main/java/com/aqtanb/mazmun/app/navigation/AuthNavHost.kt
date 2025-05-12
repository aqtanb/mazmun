package com.aqtanb.mazmun.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aqtanb.mazmun.feature.auth.SignInScreen
import com.aqtanb.mazmun.feature.auth.SignInViewModel

@Composable
fun AuthNavHost(
    navController: NavHostController,
    signInViewModel: SignInViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Routes.SIGN_IN
    ) {
        composable(Routes.SIGN_IN) {
            val state by signInViewModel.state.collectAsStateWithLifecycle()

            LaunchedEffect(state.isSignInSuccessful) {
                if (state.isSignInSuccessful) {
                    signInViewModel.resetState()
                }
            }

            SignInScreen(
                state = state,
                onSignInClick = { signInViewModel.onSignInClick() }
            )
        }
    }
}