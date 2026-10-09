package com.northwindinteractive.northwindinteractivepapertrader.data

import com.northwindinteractive.northwindinteractivepapertrader.data.alpaca.AlpacaApiService
import com.northwindinteractive.northwindinteractivepapertrader.data.alpaca.models.AlpacaAccountDto

class AlpacaRepositoryImpl(private val alpaca : AlpacaApiService) : AlpacaRepository{
    override suspend fun getAccount(): AlpacaAccountDto {
        return alpaca.getAccount()
    }

}