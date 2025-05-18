package com.aqtanb.mazmun.app.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.aqtanb.mazmun.core.domain.model.Screen

@Composable
fun MazmunNavigationBar(navController: NavHostController) {
    val tabs = listOf(
        Screen.NavigationBarScreen.Feed,
        Screen.NavigationBarScreen.Profile,
    )

    val currentRoute = navController
        .currentBackStackEntryAsState().value
        ?.destination?.route

    NavigationBar {
        tabs.forEach { screen ->
            NavigationBarItem(
                selected = currentRoute == screen.route,
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (currentRoute == screen.route) {
                            screen.selectedIcon
                        } else {
                            screen.unselectedIcon
                        },
                        contentDescription = stringResource(screen.iconTextId),
                    )
                },
                label = { Text(stringResource(screen.iconTextId)) },
                alwaysShowLabel = true,
            )
        }
    }
}
