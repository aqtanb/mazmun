package com.aqtanb.mazmun.core.domain.repository

import com.aqtanb.mazmun.core.domain.model.AuthResult
import com.aqtanb.mazmun.core.domain.model.UserData
import kotlinx.coroutines.flow.StateFlow

interface AuthRepository {
    val currentUser: StateFlow<UserData?>
    suspend fun signIn(): AuthResult
    suspend fun signOut(): AuthResult
}
