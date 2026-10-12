package com.northwindinteractive.northwindinteractivepapertrader.data

import com.northwindinteractive.northwindinteractivepapertrader.data.alpaca.models.AlpacaAccountDto
import com.northwindinteractive.northwindinteractivepapertrader.data.alpaca.models.PortfolioHistoryDto
import com.northwindinteractive.northwindinteractivepapertrader.data.alpaca.models.PositionItemDto
import com.northwindinteractive.northwindinteractivepapertrader.nav.Screen

interface AlpacaRepository {
    suspend fun getAccount( ) : AlpacaAccountDto
    suspend fun getPositions() : List<PositionItemDto>
    suspend fun closePosition(id: String)
    suspend fun openPosition()
    suspend fun getPortfolioHistory(period: String, timeframe:String): PortfolioHistoryDto
//    suspend fun getWatchList() : List<PositionDto>
//    suspend fun getStock(symbol: String): PositionDto
//    suspend fun getPopularStocks() : List<PositionDto>

}