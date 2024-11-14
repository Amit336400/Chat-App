package com.example.mychat.data.remoreRepo

import com.example.mychat.domain.model.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepo @Inject constructor(val firestore: FirebaseFirestore) {
    suspend fun saveUserData(user: User) {
        user.id?.let {
            firestore
                .collection("user")
                .document(it)
                .set(user)
                .await()
        }
    }
}