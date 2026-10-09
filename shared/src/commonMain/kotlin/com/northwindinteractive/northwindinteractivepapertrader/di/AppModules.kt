package com.northwindinteractive.northwindinteractivepapertrader.di

import com.northwindinteractive.northwindinteractivepapertrader.data.alpaca.AlpacaApiServiceImpl
import com.northwindinteractive.northwindinteractivepapertrader.data.AlpacaRepositoryImpl
import com.northwindinteractive.northwindinteractivepapertrader.data.AuthRepositoryImpl
import com.northwindinteractive.northwindinteractivepapertrader.data.AlpacaRepository
import com.northwindinteractive.northwindinteractivepapertrader.data.alpaca.AlpacaApiService
import com.northwindinteractive.northwindinteractivepapertrader.domain.repository.AuthRepository
//import com.northwindinteractive.northwindinteractivepapertrader.presentation.AlpacaViewModel
import com.northwindinteractive.northwindinteractivepapertrader.presentation.AuthViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val firebaseModule = module {

    single<AuthRepository> {
        AuthRepositoryImpl()
    }
}

val viewModelModule = module {

    viewModelOf(::AuthViewModel)
//    viewModelOf(::AlpacaViewModel)

}

val networkModule = module {

    single {
        HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                    }
                )
            }
        }
    }

//    single<AlpacaApiService> {
//        AlpacaApiServiceImpl(
//            client = get(),
//            apiKey = "PKEH4BZAYEJLFXCMWSSAHKZJZP",
//            secretKey = "AQtt82EFnVdXL5SYhptgbFpYvqY2ezmsj9gsPKib1dZ5"
//        )
//    }
//
//    single<AlpacaRepository>{
//        AlpacaRepositoryImpl(
//            alpaca = get()
//        )
//    }
}