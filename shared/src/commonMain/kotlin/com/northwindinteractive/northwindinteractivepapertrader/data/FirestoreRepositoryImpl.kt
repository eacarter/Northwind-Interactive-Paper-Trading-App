package com.northwindinteractive.northwindinteractivepapertrader.data

import com.northwindinteractive.northwindinteractivepapertrader.domain.repository.FirestoreRepository
import dev.gitlive.firebase.firestore.FirebaseFirestore

class FirestoreRepositoryImpl(
    private val firestore: FirebaseFirestore
) : FirestoreRepository{

}