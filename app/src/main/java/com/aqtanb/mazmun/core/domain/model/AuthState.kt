package com.aqtanb.mazmun.core.domain.model

data class AuthState(
    val isAuthenticated: Boolean = false,
    val user: UserData? = null
)