package com.northwindinteractive.northwindinteractivepapertrader.data.alpaca

import com.northwindinteractive.northwindinteractivepapertrader.data.AlpacaRepository
import com.northwindinteractive.northwindinteractivepapertrader.data.alpaca.models.AlpacaAccountDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
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
}