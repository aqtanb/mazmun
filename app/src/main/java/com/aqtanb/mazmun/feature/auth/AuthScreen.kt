package com.aqtanb.mazmun.feature.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AuthScreen(
    authUiState: AuthUiState,
    onSignInClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (authUiState) {
            AuthUiState.Loading -> CircularProgressIndicator()
            AuthUiState.SignedOut,
            is AuthUiState.Error -> Button(
                onClick = onSignInClick,
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(48.dp)
            ) {
                Text("Sign in with Google")
            }
            is AuthUiState.SignedIn -> {}
        }
    }
}