package com.aqtanb.mazmun.feature.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.aqtanb.mazmun.feature.search.SearchScreen
import kotlinx.serialization.Serializable

@Serializable data object SearchRoute

fun NavController.navigateToSearch(navOptions: NavOptions? = null) {
    navigate(route = SearchRoute, navOptions = navOptions)
}

fun NavGraphBuilder.searchScreen(
    onNavigateToProfile: (String) -> Unit,
    onNavigateToChannel: (String) -> Unit
) {
    composable<SearchRoute> {
        SearchScreen()
    }
}
