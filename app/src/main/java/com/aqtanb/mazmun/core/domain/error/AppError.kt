package com.aqtanb.mazmun.core.domain.error

sealed class AppError {
    sealed class AuthError : AppError() {
        object InvalidCredentials : AuthError()
        object NetworkError : AuthError()
        data class UnknownError(val message: String) : AuthError()
    }

    sealed class NetworkError : AppError() {
        object Timeout : NetworkError()
        object ServerDown : NetworkError()
    }

}