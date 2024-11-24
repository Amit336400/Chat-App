package com.example.mychat.data.remoteRepo

import com.example.mychat.domain.model.User


interface RemoteRepo {


    suspend fun saveUserData(user: User)
    suspend fun getUserWithEmail(email: String): User?

}