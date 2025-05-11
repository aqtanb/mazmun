package com.aqtanb.mazmun.core.domain.repository

import com.aqtanb.mazmun.core.domain.model.SignInResult
import com.aqtanb.mazmun.core.domain.model.UserData

interface AuthRepository {
    suspend fun signIn(): SignInResult
    suspend fun signOut()
    fun getCurrentUser(): UserData?
}