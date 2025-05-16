package com.aqtanb.mazmun.core.domain.usecase

import com.aqtanb.mazmun.core.domain.error.AppError
import com.aqtanb.mazmun.core.domain.model.AuthResult
import com.aqtanb.mazmun.core.domain.model.EmptyUserData
import com.aqtanb.mazmun.core.domain.repository.AuthRepository

class SignOutUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(): AuthResult =
        try { authRepository.signOut(); AuthResult.Success(EmptyUserData) }
        catch(e: Exception) { AuthResult.Failure(AppError.AuthError.UnknownError(e.message ?: "Unknown error")) }
}
