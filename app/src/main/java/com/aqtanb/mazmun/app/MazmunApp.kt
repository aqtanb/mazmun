package com.aqtanb.mazmun.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.aqtanb.mazmun.app.navigation.AuthNavHost
import com.aqtanb.mazmun.app.navigation.MainNavHost
import com.aqtanb.mazmun.feature.auth.SignInViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MazmunApp() {
    val navController = rememberNavController()
    val signInViewModel: SignInViewModel = koinViewModel()
    val state by signInViewModel.state.collectAsStateWithLifecycle()
    val isLoggedIn = signInViewModel.getCurrentUser() != null || state.isSignInSuccessful

    if (isLoggedIn) {
        MainNavHost(navController, signInViewModel)
    } else {
        AuthNavHost(navController, signInViewModel)
    }
}