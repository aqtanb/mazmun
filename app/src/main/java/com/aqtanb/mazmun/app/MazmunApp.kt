package com.aqtanb.mazmun.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.aqtanb.mazmun.app.navigation.MazmunNavGraph
import com.aqtanb.mazmun.core.domain.repository.AuthRepository
import org.koin.compose.koinInject

@Composable
fun MazmunApp() {
    MaterialTheme {
        val authRepository: AuthRepository = koinInject()
        MaterialTheme {
            MazmunNavGraph(authRepository)
        }
    }
}