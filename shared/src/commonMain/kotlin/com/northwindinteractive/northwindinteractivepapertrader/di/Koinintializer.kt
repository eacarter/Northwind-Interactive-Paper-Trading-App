package com.northwindinteractive.northwindinteractivepapertrader.di

import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        properties(
            mapOf(
                //TODO Alpaca api and secret keys go here for now
                "alpacaApiKey" to "",
                "alpacaSecretKey" to ""
            )
        )
        modules(
            firebaseModule,
            viewModelModule,
            networkModule,
            alpacaModule
        )
    }
}