package com.example.mychat.data.remoteRepo

import com.example.mychat.domain.model.Channel
import com.example.mychat.domain.model.Message
import com.example.mychat.domain.model.User
import kotlinx.coroutines.flow.Flow


interface RemoteRepo {


    suspend fun saveUserData(user: User)
    suspend fun getUserWithEmail(email: String): User?
    suspend fun getAllUser(): List<User>
    suspend fun getOneToOneChat(currentUserId: String, otherUserId: String): Channel?
    suspend fun createOneToOneChannel(currentUserId: String, otherUserId: String): String
    suspend fun getAllChannels(currentUser : String) : List<Channel>
    suspend fun getChannel(channelId :String) : Channel
    suspend fun sendMassage(channelId: String ,message : Message)
    suspend fun getChannelWithFlowMessage(channelId: String) : Flow<Channel >


}