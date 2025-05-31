package com.aqtanb.mazmun.feature.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aqtanb.mazmun.core.domain.model.AuthResult
import com.aqtanb.mazmun.core.domain.model.UserData
import com.aqtanb.mazmun.core.domain.usecase.SignInUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignInViewModel(
    private val signInUseCase: SignInUseCase,
    user: StateFlow<UserData?>
) : ViewModel() {
    private val _authUiState = MutableStateFlow<AuthUiState>(AuthUiState.SignedOut)
    val authUiState: StateFlow<AuthUiState> = _authUiState.asStateFlow()

    init {
        viewModelScope.launch {
            user.collect { userData ->
                if (userData == null && _authUiState.value is AuthUiState.SignedIn) {
                    _authUiState.value = AuthUiState.SignedOut
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        _authUiState.value = AuthUiState.SignedOut
    }

    fun onSignInClick() {
        viewModelScope.launch {
            _authUiState.value = AuthUiState.Loading
            _authUiState.value = when (val result = signInUseCase()) {
                is AuthResult.Success -> AuthUiState.SignedIn(result.user)
                is AuthResult.Failure -> AuthUiState.Error(result.error)
            }
        }
    }
}
