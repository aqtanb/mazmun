package com.aqtanb.mazmun.core.domain.model

import com.aqtanb.mazmun.core.domain.error.AppError

sealed interface AuthResult {
    data class Success(val user: UserData) : AuthResult
    data class Failure(val error: AppError) : AuthResult
}
