package com.northwindinteractive.northwindinteractivepapertrader.domain.repository

import com.northwindinteractive.northwindinteractivepapertrader.data.alpaca.models.AlpacaAccountDto
import com.northwindinteractive.northwindinteractivepapertrader.data.alpaca.models.AssetDto
import com.northwindinteractive.northwindinteractivepapertrader.data.alpaca.models.PortfolioHistoryDto
import com.northwindinteractive.northwindinteractivepapertrader.data.alpaca.models.PositionItemDto
import com.northwindinteractive.northwindinteractivepapertrader.data.alpaca.models.WatchListItemDto

interface AlpacaRepository {
    suspend fun getAccount( ) : AlpacaAccountDto
    suspend fun getPositions() : List<PositionItemDto>
    suspend fun closePosition(id: String)
    suspend fun openPosition()
    suspend fun getPortfolioHistory(period: String, timeframe:String): PortfolioHistoryDto
    suspend fun getWatchList() : List<WatchListItemDto>
    suspend fun getStock(symbol: String): AssetDto
//    suspend fun getPopularStocks() : List<PositionDto>

}