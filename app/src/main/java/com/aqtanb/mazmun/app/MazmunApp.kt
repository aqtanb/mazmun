package com.aqtanb.mazmun.app

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.aqtanb.mazmun.app.navigation.MazmunNavHost
import com.aqtanb.mazmun.app.navigation.MazmunNavigationBar
import com.aqtanb.mazmun.core.domain.repository.AuthRepository
import org.koin.compose.koinInject

@Composable
fun MazmunApp(authRepository: AuthRepository = koinInject()) {
    val appState = rememberMazmunAppState(authRepository = authRepository)

    Scaffold(
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
