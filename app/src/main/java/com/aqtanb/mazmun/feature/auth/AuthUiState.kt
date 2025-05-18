package com.aqtanb.mazmun.feature.auth

import com.aqtanb.mazmun.core.domain.error.AppError
import com.aqtanb.mazmun.core.domain.model.UserData

sealed interface AuthUiState {
    data object Loading : AuthUiState
    data object SignedOut : AuthUiState
    data class SignedIn(val user: UserData) : AuthUiState
    data class Error(val errorMessage: AppError) : AuthUiState
}
