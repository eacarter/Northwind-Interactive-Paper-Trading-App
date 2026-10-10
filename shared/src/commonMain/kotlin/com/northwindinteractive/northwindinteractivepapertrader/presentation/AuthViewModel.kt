package com.northwindinteractive.northwindinteractivepapertrader.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.northwindinteractive.northwindinteractivepapertrader.domain.repository.AuthRepository
import com.northwindinteractive.northwindinteractivepapertrader.domain.repository.FirestoreRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authRepository: AuthRepository,
    private val firestore: FirestoreRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow<LoginUiState>(
        LoginUiState.Idle)
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()


    fun signIn(email: String, password: String) {

        viewModelScope.launch {

            _uiState.update { LoginUiState.Loading }

            authRepository
                .signIn(
                    email = email,
                    password = password
                )
                .onSuccess {
                   _uiState.update { LoginUiState.Success }
                }
                .onFailure { throwable ->
                    _uiState.update { LoginUiState.Error(throwable.message.toString()) }
                }
        }
    }

    fun signUp(email: String, password: String, apikey: String = "", secretkey: String) {
        viewModelScope.launch {

            _uiState.update { LoginUiState.Loading }

            authRepository.signUp(
                    email = email,
                    password = password,
                    mapOf(
                        "apiKey" to apikey,
                        "secret" to secretkey
                    )
                )
                .onSuccess {
                    _uiState.update { LoginUiState.Success }
                }
                .onFailure { throwable ->
                    _uiState.update { LoginUiState.Error(throwable.message.toString()) }
                }
        }
    }

    fun sighOut(){
        viewModelScope.launch {
            _uiState.update { LoginUiState.Loading }
            authRepository.signOut()
            _uiState.update {
                LoginUiState.Idle
            }
        }
    }
}
sealed interface LoginUiState {
    object Idle: LoginUiState
    object Loading : LoginUiState
    object Success : LoginUiState
    data class Error(val message: String) : LoginUiState
}