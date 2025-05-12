package com.aqtanb.mazmun.feature.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aqtanb.mazmun.core.domain.error.AppError
import com.aqtanb.mazmun.core.domain.model.SignInResult
import com.aqtanb.mazmun.core.domain.model.UserData
import com.aqtanb.mazmun.core.domain.repository.AuthRepository
import com.aqtanb.mazmun.core.domain.usecase.SignInUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SignInViewModel(
    private val authRepo: AuthRepository,
    private val signInUseCase: SignInUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(SignInState())
    val state: StateFlow<SignInState> = _state

    fun onSignInClick() {
        viewModelScope.launch {
            _state.value = SignInState(isLoading = true)

            when(val result = signInUseCase()) {
                is SignInResult.Success -> handleSuccess(result.user)
                is SignInResult.Error -> handleError(result.error)
                SignInResult.Loading -> {}
            }
        }
    }

    private fun handleSuccess(user: UserData) {
        _state.value = SignInState(
            isSignInSuccessful = true,
            user = user
        )
    }

    private fun handleError(error: AppError) {
        _state.value = SignInState(
            error = error,
            errorMessage = mapErrorToMessage(error)
        )
    }

    private fun mapErrorToMessage(error: AppError): String {
        return when(error) {
            is AppError.AuthError.InvalidCredentials -> "Invalid credentials"
            is AppError.AuthError.NetworkError -> "Connection timeout"
            else -> "Unknown error occurred"
        }
    }

    fun onSignOutClick() {
        viewModelScope.launch {
            authRepo.signOut()
            _state.value = SignInState()
            resetState()
        }
    }

    fun resetState() {
        _state.value = SignInState()
    }

    fun getCurrentUser(): UserData? {
        return authRepo.getCurrentUser()
    }
}