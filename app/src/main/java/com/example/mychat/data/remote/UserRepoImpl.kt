package com.example.mychat.data.remote

import com.example.mychat.data.remote.FireBaseCollection.userChannel
import com.example.mychat.data.remote.FireBaseCollection.userCollection
import com.example.mychat.domain.model.Channel
import com.example.mychat.domain.model.Message
import com.example.mychat.domain.model.User
import com.example.mychat.domain.remote.UserRepo
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepoImpl @Inject constructor(
    val firestore: FirebaseFirestore
): UserRepo {

    /**
     * User data Save in the firebase database
     */
    override suspend fun saveUserData(user: User) {
        user.id?.let {
            firestore
                .userCollection()
                .document(it)
                .set(user)
                .await()
        }
    }

    /**
     * Check user is login before or not
     */
    override suspend fun getUserWithEmail(email: String): User? {
        return firestore.userCollection()
            .whereEqualTo(User::email.name, email)
            .get()
            .await()
            .toObjects(User::class.java)
            .firstOrNull()
    }

    override suspend fun getAllUser(): List<User> {
        return firestore.userCollection()
            .get()
            .await()
            .toObjects(User::class.java)

    }


}