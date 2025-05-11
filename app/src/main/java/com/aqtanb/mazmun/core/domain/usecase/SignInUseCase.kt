package com.aqtanb.mazmun.core.domain.usecase

import com.aqtanb.mazmun.core.domain.error.AppError
import com.aqtanb.mazmun.core.domain.model.SignInResult
import com.aqtanb.mazmun.core.domain.repository.AuthRepository
import java.io.IOException

class SignInUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(): SignInResult {
        return try {
            when(val result = repository.signIn()) {
                is SignInResult.Success -> result
                is SignInResult.Error -> result
                SignInResult.Loading -> SignInResult.Error(AppError.AuthError.UnknownError("Unknown"))
            }
        } catch (e: Exception) {
            SignInResult.Error(mapExceptionToError(e))
        }
    }

    private fun mapExceptionToError(e: Exception): AppError {
        return when(e) {
            is IOException -> AppError.NetworkError.Timeout
            else -> AppError.AuthError.UnknownError(e.message ?: "Unknown error")
        }
    }
}