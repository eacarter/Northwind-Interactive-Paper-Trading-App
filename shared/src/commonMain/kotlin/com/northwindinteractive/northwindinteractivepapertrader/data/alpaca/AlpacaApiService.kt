package com.northwindinteractive.northwindinteractivepapertrader.data.alpaca

import com.northwindinteractive.northwindinteractivepapertrader.data.alpaca.models.AlpacaAccountDto

interface AlpacaApiService {
    suspend fun getAccount(): AlpacaAccountDto
}