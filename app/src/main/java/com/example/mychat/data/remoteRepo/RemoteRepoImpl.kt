package com.example.mychat.data.remoteRepo

import com.example.mychat.data.remoteRepo.FireBaseCollection.userChannel
import com.example.mychat.data.remoteRepo.FireBaseCollection.userCollection
import com.example.mychat.domain.model.Channel
import com.example.mychat.domain.model.Message
import com.example.mychat.domain.model.User
import com.google.firebase.firestore.FieldValue
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


    override suspend fun getOneToOneChat(
        currentUserId: String,
        otherUserId: String,
    ): Channel? {
        return firestore.userChannel()
            .whereEqualTo(Channel::type.name, Channel.Type.OneToOne)
            .whereArrayContainsAny(Channel::members.name, listOf(currentUserId, otherUserId))
            .get()
            .await()
            .toObjects(Channel::class.java)
            .firstOrNull {
                it.members == listOf(currentUserId, otherUserId) ||
                it.members == listOf(currentUserId, otherUserId)
            }
    }

    override suspend fun createOneToOneChannel(currentUserId: String, otherUserId: String): String {

        val culRef = firestore.userChannel()
        val id = culRef.document().id

        culRef.document(id).set(
            Channel(
                imageUrl = null,
                name = "Amit",
                type = Channel.Type.OneToOne,
                description = null,
                members = listOf(currentUserId, otherUserId),
                messages = emptyList(),
            )

        ).await()

        return id
    }

    override suspend fun getAllChannels(currentUser: String): List<Channel> {
        return firestore.userChannel()
            .whereEqualTo(Channel::type.name, Channel.Type.OneToOne)
            .whereArrayContains(Channel::members.name, currentUser)
            .get()
            .await()
            .toObjects(Channel::class.java)
    }

    override suspend fun getChannel(channelId: String): Channel {
        return firestore.userChannel()
            .document(channelId)
            .get()
            .await()
            .toObject(Channel::class.java)?: error("Channel Not Found $channelId")
    }

    override suspend fun sendMassage(channelId: String, message: Message) {
        firestore.userChannel()
            .document(channelId)
            .update(Channel::messages.name , FieldValue.arrayUnion(message))
            .await()
    }

}