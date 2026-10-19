package com.northwindinteractive.northwindinteractivepapertrader.di

import org.koin.core.context.startKoin
import org.koin.mp.KoinPlatform

fun initKoin() {
    val koinInstance = KoinPlatform.getKoinOrNull()

    if(koinInstance != null){
        print("Instance already started")
    }
    else {
        startKoin {
            properties(
                mapOf(
                    //TODO Alpaca api and secret keys go here for now
                    "alpacaApiKey" to "",
                    "alpacaSecretKey" to "",
                    "aiApiKey" to ""
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
}