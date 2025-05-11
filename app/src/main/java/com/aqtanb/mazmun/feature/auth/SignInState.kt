package com.aqtanb.mazmun.feature.auth

import com.aqtanb.mazmun.core.domain.error.AppError
import com.aqtanb.mazmun.core.domain.model.UserData

data class SignInState(
    val isLoading: Boolean = false,
    val isSignInSuccessful: Boolean = false,
    val user: UserData? = null,
    val error: AppError? = null,
    val errorMessage: String? = null
)