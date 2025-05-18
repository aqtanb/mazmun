package com.aqtanb.mazmun.feature.profile.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.aqtanb.mazmun.core.domain.model.Screen
import com.aqtanb.mazmun.feature.profile.ProfileScreen
import com.aqtanb.mazmun.feature.profile.ProfileViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.profileGraph() {
    composable(route = Screen.NavigationBarScreen.Profile.route) {
        val profileViewModel: ProfileViewModel = koinViewModel()
        val userData by profileViewModel.userData.collectAsStateWithLifecycle()
        ProfileScreen(
            userData = userData,
            onSignOutClick = {
                profileViewModel.onSignOut()
            },
        )
    }
}
