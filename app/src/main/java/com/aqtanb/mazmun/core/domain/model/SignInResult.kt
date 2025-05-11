package com.aqtanb.mazmun.core.domain.model

import com.aqtanb.mazmun.core.domain.error.AppError

sealed class SignInResult {
    data class Success(val user: UserData) : SignInResult()
    data class Error(val error: AppError) : SignInResult()
    object Loading : SignInResult()
}
