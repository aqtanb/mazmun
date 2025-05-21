package com.aqtanb.mazmun.core.domain.model

import com.aqtanb.mazmun.core.model.UserData

data class AuthState(
    val isAuthenticated: Boolean = false,
    val user: UserData? = null
)
