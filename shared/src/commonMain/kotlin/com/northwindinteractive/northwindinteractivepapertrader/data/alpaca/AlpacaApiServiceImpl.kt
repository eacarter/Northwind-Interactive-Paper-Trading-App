package com.northwindinteractive.northwindinteractivepapertrader.data.alpaca

import com.northwindinteractive.northwindinteractivepapertrader.data.AlpacaRepository
import com.northwindinteractive.northwindinteractivepapertrader.data.alpaca.models.AlpacaAccountDto
import com.northwindinteractive.northwindinteractivepapertrader.data.alpaca.models.PortfolioHistoryDto
import com.northwindinteractive.northwindinteractivepapertrader.data.alpaca.models.PositionItemDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText

class AlpacaApiServiceImpl( private val client: HttpClient,
                            private val apiKey: String,
                            private val secretKey: String) : AlpacaApiService {

    private val baseUrl = "https://paper-api.alpaca.markets"

    override suspend fun getAccount(): AlpacaAccountDto {
        val response = client.get("$baseUrl/v2/account") {
            header("APCA-API-KEY-ID", apiKey)
            header("APCA-API-SECRET-KEY", secretKey)
        }

        println("HTTP Status: ${response.status}")
        println("Raw Response: ${response.bodyAsText()}")

        return response.body()
    }

    override suspend fun getPositions(): List<PositionItemDto> {
        val response = client.get("$baseUrl/v2/positions") {
            header("APCA-API-KEY-ID", apiKey)
            header("APCA-API-SECRET-KEY", secretKey)
        }

        println("HTTP Status: ${response.status}")
        println("Raw Response: ${response.bodyAsText()}")

        return response.body()
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
        val response = client.get("$baseUrl/v2/account/portfolio/history") {
            header("APCA-API-KEY-ID", apiKey)
            header("APCA-API-SECRET-KEY", secretKey)
            parameter("period", period)
            parameter("timeframe", timeframe)
        }

        println("HTTP Status: ${response.status}")
        println("Raw Response: ${response.bodyAsText()}")

        return response.body()

    }

//    override suspend fun getWatchList(): List<PositionDto> {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun getStock(symbol: String): PositionDto {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun getPopularStocks(): List<PositionDto> {
//        TODO("Not yet implemented")
//    }
}