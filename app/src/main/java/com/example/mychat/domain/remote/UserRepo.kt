package com.example.mychat.domain.remote

import com.example.mychat.domain.model.Channel
import com.example.mychat.domain.model.Message
import com.example.mychat.domain.model.User
import kotlinx.coroutines.flow.Flow


interface UserRepo {


    suspend fun saveUserData(user: User)
    suspend fun getUserWithEmail(email: String): User?
    suspend fun getAllUser(): List<User>


}