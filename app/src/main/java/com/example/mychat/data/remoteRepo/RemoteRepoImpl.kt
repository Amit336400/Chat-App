package com.example.mychat.data.remoteRepo

import com.example.mychat.data.remoteRepo.FireBaseCollection.userCollection
import com.example.mychat.domain.model.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RemoteRepoImpl @Inject constructor(
    val firestore: FirebaseFirestore
): RemoteRepo {

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