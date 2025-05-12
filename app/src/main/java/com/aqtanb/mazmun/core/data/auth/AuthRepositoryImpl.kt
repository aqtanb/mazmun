package com.aqtanb.mazmun.core.data.auth

import com.aqtanb.mazmun.core.domain.error.AppError
import com.aqtanb.mazmun.core.domain.model.SignInResult
import com.aqtanb.mazmun.core.domain.model.UserData
import com.aqtanb.mazmun.core.domain.repository.AuthRepository
import java.io.IOException

class AuthRepositoryImpl(
    private val googleAuthService: GoogleAuthService
) : AuthRepository {
    override suspend fun signIn(): SignInResult {
        return try {
            googleAuthService.signIn()
        } catch (e: Exception) {
            SignInResult.Error(mapExceptionToError(e))
        }
    }

    private fun mapExceptionToError(e: Exception): AppError {
        return when(e) {
            is IOException -> AppError.AuthError.NetworkError
            else -> AppError.AuthError.UnknownError(e.message ?: "Unknown error")
        }
    }
    override suspend fun signOut() = googleAuthService.signOut()
    override fun getCurrentUser(): UserData? = googleAuthService.getSignedInUser()
}