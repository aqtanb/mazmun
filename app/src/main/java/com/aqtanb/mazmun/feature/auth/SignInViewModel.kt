package com.aqtanb.mazmun.feature.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aqtanb.mazmun.core.domain.model.AuthResult
import com.aqtanb.mazmun.core.domain.usecase.SignInUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignInViewModel(
    private val signInUseCase: SignInUseCase
) : ViewModel() {
    private val _authUiState = MutableStateFlow<AuthUiState>(AuthUiState.SignedOut)
    val authUiState: StateFlow<AuthUiState> = _authUiState.asStateFlow()

    fun onSignInClick() {
        viewModelScope.launch {
            _authUiState.value = AuthUiState.Loading
            when (val result = signInUseCase()) {
                is AuthResult.Success -> AuthUiState.SignedIn(result.user)
                is AuthResult.Failure -> AuthUiState.Error(result.error)
            }
        }
    }
}
