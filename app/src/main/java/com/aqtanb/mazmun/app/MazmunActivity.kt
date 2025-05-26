package com.aqtanb.mazmun.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.aqtanb.mazmun.core.domain.repository.AuthRepository
import com.aqtanb.mazmun.core.ui.theme.MazmunTheme
import org.koin.compose.koinInject

class MazmunActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MazmunTheme {
                val authRepository: AuthRepository = koinInject()
                val appState: MazmunAppState = rememberMazmunAppState(authRepository = authRepository)
                MazmunApp(appState = appState)
            }
        }
    }
}
