package com.aqtanb.mazmun.feature.profile.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.aqtanb.mazmun.core.model.UserData
import com.aqtanb.mazmun.feature.profile.ProfileScreen
import com.aqtanb.mazmun.feature.profile.ProfileViewModel
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable data object ProfileRoute

@Serializable data class UserProfileRoute(val userId: String)

fun NavController.navigateToProfile(navOptions: NavOptions? = null) {
    navigate(route = ProfileRoute, navOptions = navOptions)
}

fun NavController.navigateToUserProfile(
    userId: String,
    navOptions: NavOptions? = null
) {
    navigate(route = UserProfileRoute(userId), navOptions = navOptions)
}

fun NavGraphBuilder.profileScreen(
    userData: UserData?,
    onSignOut: () -> Unit
) {
    composable<ProfileRoute> {
        val profileViewModel: ProfileViewModel = koinViewModel()
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
