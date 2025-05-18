package com.aqtanb.mazmun.core.domain.repository

import com.aqtanb.mazmun.core.domain.model.AuthResult
import com.aqtanb.mazmun.core.domain.model.UserData
import kotlinx.coroutines.flow.StateFlow

interface AuthRepository {
    suspend fun signIn(): AuthResult
    suspend fun signOut(): AuthResult
    val currentUser: StateFlow<UserData?>
}
