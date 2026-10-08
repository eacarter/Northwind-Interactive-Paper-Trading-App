package com.northwindinteractive.northwindinteractivepapertrader.di

import com.northwindinteractive.northwindinteractivepapertrader.data.AuthRepositoryImpl
import com.northwindinteractive.northwindinteractivepapertrader.domain.repository.AuthRepository
import com.northwindinteractive.northwindinteractivepapertrader.presentation.AuthViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val firebaseModule = module {

    single<AuthRepository> {
        AuthRepositoryImpl()
    }
}

val viewModelModule = module {

    viewModelOf(::AuthViewModel)

}