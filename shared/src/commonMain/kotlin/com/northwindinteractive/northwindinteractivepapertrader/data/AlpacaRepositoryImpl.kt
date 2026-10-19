package com.northwindinteractive.northwindinteractivepapertrader.data

import com.northwindinteractive.northwindinteractivepapertrader.data.alpaca.AlpacaApiService
import com.northwindinteractive.northwindinteractivepapertrader.data.alpaca.models.AlpacaAccountDto
import com.northwindinteractive.northwindinteractivepapertrader.data.alpaca.models.AssetDto
import com.northwindinteractive.northwindinteractivepapertrader.data.alpaca.models.PortfolioHistoryDto
import com.northwindinteractive.northwindinteractivepapertrader.data.alpaca.models.PositionItemDto
import com.northwindinteractive.northwindinteractivepapertrader.data.alpaca.models.WatchListItemDto
import com.northwindinteractive.northwindinteractivepapertrader.domain.repository.AlpacaRepository

class AlpacaRepositoryImpl(private val alpaca : AlpacaApiService) : AlpacaRepository {
    override suspend fun getAccount(): AlpacaAccountDto {
        return alpaca.getAccount()
    }

    override suspend fun getPositions(): List<PositionItemDto> {
        return alpaca.getPositions()
    }

    override suspend fun closePosition(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun openPosition() {
        TODO("Not yet implemented")
    }

    override suspend fun getPortfolioHistory(
        period: String,
        timeframe: String
    ): PortfolioHistoryDto {
        return alpaca.getPortfolioHistory(period, timeframe)
    }

    override suspend fun getWatchList(): List<WatchListItemDto> {
        return alpaca.getWatchList()
    }

    override suspend fun getStock(symbol: String): AssetDto {
        return alpaca.getStock(symbol)
    }
//
//    override suspend fun getPopularStocks(): List<PositionDto> {
//        TODO("Not yet implemented")
//    }

}