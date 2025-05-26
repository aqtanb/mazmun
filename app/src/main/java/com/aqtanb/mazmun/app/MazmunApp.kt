package com.aqtanb.mazmun.app

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.aqtanb.mazmun.app.navigation.MazmunNavHost
import com.aqtanb.mazmun.app.navigation.MazmunNavigationBar
import com.aqtanb.mazmun.app.navigation.MazmunTopAppBar

@Composable
fun MazmunApp(appState: MazmunAppState) {
    Scaffold(
        topBar = {
            if (appState.shouldShowTopBar) {
                MazmunTopAppBar(
                    appBarTitle = appState.currentTopLevelDestination?.titleTextId?.let { stringResource(it) }
                        ?: "",
                )
            }
        },
        bottomBar = {
            if (appState.shouldShowBottomBar) {
                MazmunNavigationBar(
                    destinations = appState.topLevelDestinations,
                    onNavigateToDestination = appState::navigateToTopLevelDestination,
                    currentDestination = appState.currentDestination,
                )
            }
        },
    ) { padding ->
        MazmunNavHost(
            appState = appState,
            modifier = Modifier.padding(padding),
        )
    }
}
