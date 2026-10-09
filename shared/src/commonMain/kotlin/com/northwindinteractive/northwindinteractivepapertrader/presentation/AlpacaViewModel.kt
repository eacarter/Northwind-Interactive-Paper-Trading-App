package com.northwindinteractive.northwindinteractivepapertrader.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.northwindinteractive.northwindinteractivepapertrader.data.alpaca.models.AlpacaAccountDto
import com.northwindinteractive.northwindinteractivepapertrader.data.AlpacaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

class AlpacaViewModel (private val alpacaRepository: AlpacaRepository): ViewModel(){

    private val _account =
        MutableStateFlow<AlpacaAccountDto?>(null)

    val account = _account.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    fun loadAccount() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                _account.value = alpacaRepository.getAccount()
                print("Account: ${_account.value!!.portfolioValue}")
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _error.value = e.message ?: "Unable to load account"
            } finally {
                _isLoading.value = false
            }
        }
    }
}