package com.northwindinteractive.northwindinteractivepapertrader.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.northwindinteractive.northwindinteractivepapertrader.data.alpaca.models.AlpacaAccountDto
import com.northwindinteractive.northwindinteractivepapertrader.domain.repository.AlpacaRepository
import com.northwindinteractive.northwindinteractivepapertrader.data.alpaca.models.PortfolioHistoryDto
import com.northwindinteractive.northwindinteractivepapertrader.data.alpaca.models.PositionItemDto
import com.northwindinteractive.northwindinteractivepapertrader.data.alpaca.models.WatchListItemDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import kotlin.coroutines.cancellation.CancellationException

class AlpacaViewModel (private val alpacaRepository: AlpacaRepository): ViewModel(){

    private val _account = MutableStateFlow<AlpacaAccountDto?>(null)
    private val _positions = MutableStateFlow<List<PositionItemDto>>(emptyList())
    private val _portHistory = MutableStateFlow<PortfolioHistoryDto?>(null)
    private val _watchList = MutableStateFlow<List<WatchListItemDto>>(emptyList())

    val account = _account.asStateFlow()
    val positions = _positions.asStateFlow()
    val portHistory = _portHistory.asStateFlow()
    val watchList = _watchList.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    init {
        refreshPortfolio()
    }

    fun refreshPortfolio(period: String = "1M", timeframe: String = "1D") {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try{
                supervisorScope {
                    launch{
                        loadAccount()
                    }
                    launch{
                        loadPositions()
                    }
                    launch{
                        loadPortfolioHistory(period, timeframe)
                    }
                    launch{
                        loadWatchList()
                    }
                }
            }
            finally {
                _isLoading.value = false
            }
        }
    }

    fun loadAccount() {
        viewModelScope.launch {
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
    fun loadPositions(){
        viewModelScope.launch {
            try {
                val result = alpacaRepository.getPositions()
                _positions.value = result
            } catch (e: CancellationException) {
                println("CANCELLED: ${e.message}")
                throw e

            } catch (e: Exception) {
                println("ERROR: ${e.message}")
                e.printStackTrace()
                _error.value = e.message ?: "Unable to load positions"
            }
        }
    }

    fun loadPortfolioHistory(period: String, timeframe: String){
        viewModelScope.launch{
            try {
                val result = alpacaRepository.getPortfolioHistory(period, timeframe)
                _portHistory.value = result
            } catch (e: CancellationException) {
                println("CANCELLED: ${e.message}")
                throw e

            } catch (e: Exception) {
                println("ERROR: ${e.message}")
                e.printStackTrace()
                _error.value = e.message ?: "Unable to load positions"
            }
        }
    }

    fun loadWatchList(){
        viewModelScope.launch {
            try {
                val result = alpacaRepository.getWatchList()
                _watchList.value = result
            } catch (e: CancellationException) {
                println("CANCELLED: ${e.message}")
                throw e

            } catch (e: Exception) {
                println("ERROR: ${e.message}")
                e.printStackTrace()
                _error.value = e.message ?: "Unable to load positions"
            }
        }
    }
}