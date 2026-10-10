package com.northwindinteractive.northwindinteractivepapertrader.data

import com.northwindinteractive.northwindinteractivepapertrader.domain.model.User
import com.northwindinteractive.northwindinteractivepapertrader.domain.repository.AuthRepository
import com.northwindinteractive.northwindinteractivepapertrader.domain.repository.FirestoreRepository
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore

class AuthRepositoryImpl : AuthRepository{

    private val auth = Firebase.auth
    private val firestore = Firebase.firestore

    override val currentUser: User?
        get() = auth.currentUser?.let { firebaseUser ->
            User(
                id = firebaseUser.uid,
                email = firebaseUser.email.toString()
            )
        }

    override suspend fun signUp(email: String, password: String, keys: Map<String, String>): Result<User> {
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
                ).also {
                    userCreation(
                        it.id,
                        keys = keys,
                    )
                }

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

    fun userCreation(uid: String, keys: Map<String, String>) {
        firestore.collection("Users")
            .document(uid)
            .collection("Profile")
            .document(keys.toString())
    }
}