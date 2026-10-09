package com.northwindinteractive.northwindinteractivepapertrader.di

import com.northwindinteractive.northwindinteractivepapertrader.data.AlpacaRepository
import com.northwindinteractive.northwindinteractivepapertrader.data.AlpacaRepositoryImpl
import com.northwindinteractive.northwindinteractivepapertrader.data.alpaca.AlpacaApiService
import com.northwindinteractive.northwindinteractivepapertrader.data.alpaca.AlpacaApiServiceImpl
import com.northwindinteractive.northwindinteractivepapertrader.presentation.AlpacaViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.koin.plugin.module.dsl.viewModel

val alpacaModule = module {

    single<HttpClient> {
        HttpClient {

            expectSuccess = true
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                    }
                )
            }
        }
    }

    single<AlpacaApiService> {
        AlpacaApiServiceImpl(
            client = get(),
            apiKey = getProperty("alpacaApiKey"),
            secretKey = getProperty("alpacaSecretKey")
        )
    }

    single<AlpacaRepository> {
        AlpacaRepositoryImpl(
            alpaca = get()
        )
    }

    viewModel {
        AlpacaViewModel(
            alpacaRepository = get()
        )
    }
}