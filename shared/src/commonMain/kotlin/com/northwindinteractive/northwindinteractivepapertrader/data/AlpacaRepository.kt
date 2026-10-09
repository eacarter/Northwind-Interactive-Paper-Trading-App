package com.northwindinteractive.northwindinteractivepapertrader.data

import com.northwindinteractive.northwindinteractivepapertrader.data.alpaca.models.AlpacaAccountDto

interface AlpacaRepository {
    suspend fun getAccount( ) : AlpacaAccountDto
}