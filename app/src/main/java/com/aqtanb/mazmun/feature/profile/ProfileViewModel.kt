package com.aqtanb.mazmun.feature.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aqtanb.mazmun.core.domain.model.AuthResult
import com.aqtanb.mazmun.core.domain.usecase.SignOutUseCase
import com.aqtanb.mazmun.feature.auth.AuthUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val signOutUseCase: SignOutUseCase
) : ViewModel() {
    private val _authUiState = MutableStateFlow<AuthUiState>(AuthUiState.SignedOut)
    val authUiState: StateFlow<AuthUiState> = _authUiState.asStateFlow()

    fun onSignOut() {
        viewModelScope.launch {
            _authUiState.value = AuthUiState.Loading
            _authUiState.value = when (val result = signOutUseCase()) {
                is AuthResult.Success -> AuthUiState.SignedOut
                is AuthResult.Failure -> AuthUiState.Error(result.error)
            }
        }
    }
}
