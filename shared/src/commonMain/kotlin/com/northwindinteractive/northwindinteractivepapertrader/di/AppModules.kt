package com.northwindinteractive.northwindinteractivepapertrader.di

import com.northwindinteractive.northwindinteractivepapertrader.data.alpaca.AlpacaApiServiceImpl
import com.northwindinteractive.northwindinteractivepapertrader.data.AlpacaRepositoryImpl
import com.northwindinteractive.northwindinteractivepapertrader.data.AuthRepositoryImpl
import com.northwindinteractive.northwindinteractivepapertrader.data.AlpacaRepository
import com.northwindinteractive.northwindinteractivepapertrader.data.FirestoreRepositoryImpl
import com.northwindinteractive.northwindinteractivepapertrader.data.alpaca.AlpacaApiService
import com.northwindinteractive.northwindinteractivepapertrader.domain.repository.AuthRepository
import com.northwindinteractive.northwindinteractivepapertrader.domain.repository.FirestoreRepository
//import com.northwindinteractive.northwindinteractivepapertrader.presentation.AlpacaViewModel
import com.northwindinteractive.northwindinteractivepapertrader.presentation.AuthViewModel
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.firestore
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val firebaseModule = module {

    single<FirebaseAuth>{
        Firebase.auth
    }

    single<FirebaseFirestore>{
        Firebase.firestore
    }

    single<FirestoreRepository>{
        FirestoreRepositoryImpl(
            firestore = get()
        )
    }

    single<AuthRepository> {
        AuthRepositoryImpl()
    }
}

val viewModelModule = module {

    viewModelOf(::AuthViewModel)

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
}