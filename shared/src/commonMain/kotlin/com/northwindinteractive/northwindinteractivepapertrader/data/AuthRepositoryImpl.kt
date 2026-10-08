package com.northwindinteractive.northwindinteractivepapertrader.data

import com.northwindinteractive.northwindinteractivepapertrader.domain.model.User
import com.northwindinteractive.northwindinteractivepapertrader.domain.repository.AuthRepository
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth

class AuthRepositoryImpl : AuthRepository {

    private val auth = Firebase.auth

    override val currentUser: User?
        get() = auth.currentUser?.let { firebaseUser ->
            User(
                id = firebaseUser.uid,
                email = firebaseUser.email.toString()
            )
        }

    override suspend fun signUp(email: String, password: String): Result<User> {
        return try {

            val result =
                auth.createUserWithEmailAndPassword(
                    email = email,
                    password = password
                )

            val firebaseUser = result.user

            Result.success(
                User(
                    id = firebaseUser!!.uid,
                    email = firebaseUser.email
                )
            )

        } catch (e: Exception) {

            Result.failure(e)

        }
    }

    override suspend fun signIn(email: String, password: String): Result<User> {
        return try {

            val result =
                auth.signInWithEmailAndPassword(
                    email = email,
                    password = password
                )

            val firebaseUser = result.user

            Result.success(
                User(
                    id = firebaseUser!!.uid,
                    email = firebaseUser.email
                )
            )

        } catch (e: Exception) {

            Result.failure(e)

        }
    }

    override suspend fun signOut() {
        auth.signOut()
    }
}