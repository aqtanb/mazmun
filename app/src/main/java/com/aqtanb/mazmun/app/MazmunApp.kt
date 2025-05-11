package com.aqtanb.mazmun.app

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.aqtanb.mazmun.core.ui.theme.MazmunTheme

//@Composable
//fun MazmunApp() {
//    MazmunTheme {
//        Surface(color = MaterialTheme.colorScheme.background) {
//            val snackbarHostState = remember { SnackbarHostState() }
//            val appState = rememberAppState(snackbarHostState)
//
//            Scaffold(
//                snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
//            ) { innerPaddingModifier ->
//                NavHost(
//                    navController = appState.navController,
//                    startDestination = AUTH_SCREEN,
//                    modifier = Modifier.padding(innerPaddingModifier)
//                ) {
//                    notesGraph(appState)
//                }
//            }
//        }
//    }
//}