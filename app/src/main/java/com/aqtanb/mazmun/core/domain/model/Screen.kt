package com.aqtanb.mazmun.core.domain.model

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.aqtanb.mazmun.R

sealed class Screen(val route: String) {
    sealed class NavigationBarScreen(
        route: String,
        val selectedIcon: ImageVector,
        val unselectedIcon: ImageVector,
        @StringRes val iconTextId: Int,
        @StringRes val titleTextId: Int
    ) : Screen(route) {
        object Feed : NavigationBarScreen(
            route = "main/feed",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            iconTextId = R.string.feature_feed_title,
            titleTextId = R.string.app_name,
        )
        object Profile : NavigationBarScreen(
            route = "main/profile",
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Outlined.Person,
            iconTextId = R.string.feature_profile_title,
            titleTextId = R.string.app_name,
        )
    }
    object SignIn : Screen("auth/signin")
}
