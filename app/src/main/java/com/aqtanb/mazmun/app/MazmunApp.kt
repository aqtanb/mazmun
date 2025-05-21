package com.aqtanb.mazmun.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import com.aqtanb.mazmun.app.navigation.MazmunNavGraph
import com.aqtanb.mazmun.feature.auth.SignInViewModel
import org.koin.compose.koinInject

@Composable
fun MazmunApp() {
    val navController = rememberNavController()
    val signInViewModel: SignInViewModel = koinInject()
    val user by signInViewModel.user.collectAsState()

    MazmunNavGraph(
        user = user,
        navController = navController,
    )
}
