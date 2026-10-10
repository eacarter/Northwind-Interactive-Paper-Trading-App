package com.northwindinteractive.northwindinteractivepapertrader.domain.repository

import com.northwindinteractive.northwindinteractivepapertrader.domain.model.User

interface AuthRepository {
    val currentUser: User?

    suspend fun signUp(
        email: String,
        password: String,
        keys: Map<String, String>
    ): Result<User>

    suspend fun signIn(
        email: String,
        password: String
    ): Result<User>

    suspend fun signOut()
}