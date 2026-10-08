package com.northwindinteractive.northwindinteractivepapertrader.di

import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(
            firebaseModule,
            viewModelModule
        )
    }
}