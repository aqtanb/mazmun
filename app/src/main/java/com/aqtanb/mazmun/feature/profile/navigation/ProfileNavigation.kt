package com.aqtanb.mazmun.feature.profile.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.aqtanb.mazmun.app.navigation.Screen
import com.aqtanb.mazmun.feature.profile.ProfileScreen
import com.aqtanb.mazmun.feature.profile.ProfileViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.profileScreen(onSignOut: () -> Unit) {
    composable(route = Screen.NavigationBarScreen.Profile.route) {
        val profileViewModel: ProfileViewModel = koinViewModel()
        val userData by profileViewModel.userData.collectAsStateWithLifecycle()
        val authUiState by profileViewModel.authUiState.collectAsStateWithLifecycle()
        ProfileScreen(
            authUiState = authUiState,
            userData = userData,
            onSignOutClick = {
                profileViewModel.onSignOut()
                onSignOut()
            },
        )
    }
}
