package com.aqtanb.mazmun.core.domain.error

sealed interface AppError {
    sealed interface AuthError : AppError {
        object InvalidCredentials : AuthError
        object NetworkError : AuthError
        data class UnknownError(val message: String) : AuthError
    }
}