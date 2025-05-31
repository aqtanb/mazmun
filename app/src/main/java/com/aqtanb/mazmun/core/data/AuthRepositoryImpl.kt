package com.aqtanb.mazmun.core.data

import com.aqtanb.mazmun.core.domain.error.AppError
import com.aqtanb.mazmun.core.domain.model.AuthResult
import com.aqtanb.mazmun.core.domain.model.EmptyUserData
import com.aqtanb.mazmun.core.domain.model.UserData
import com.aqtanb.mazmun.core.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.IOException

class AuthRepositoryImpl(
    private val googleAuthService: GoogleAuthService
) : AuthRepository {
    private val _currentUser = MutableStateFlow(googleAuthService.getSignedInUser())
    override val currentUser: StateFlow<UserData?> = _currentUser.asStateFlow()
    override suspend fun signIn(): AuthResult {
        val res = try {
            googleAuthService.signIn()
        } catch (e: IOException) {
            AuthResult.Failure(AppError.AuthError.NetworkError)
        } catch (e: Exception) {
            AuthResult.Failure(AppError.AuthError.UnknownError(e.message ?: "Unknown"))
        }

        if (res is AuthResult.Success) {
            _currentUser.value = res.user
        }
        return res
    }

    override suspend fun signOut(): AuthResult {
        val res = try {
            googleAuthService.signOut()
            AuthResult.Success(EmptyUserData)
        } catch (e: IOException) {
            AuthResult.Failure(AppError.AuthError.NetworkError)
        } catch (e: Exception) {
            AuthResult.Failure(AppError.AuthError.UnknownError(e.message ?: "Unknown error"))
        }
        if (res is AuthResult.Success) {
            _currentUser.value = null
        }
        return res
    }
}
