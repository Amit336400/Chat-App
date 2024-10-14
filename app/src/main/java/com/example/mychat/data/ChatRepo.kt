package com.example.mychat.data

import kotlinx.coroutines.flow.Flow

interface ChatRepo{
    suspend fun saveLoginState(isLoggedIn: Boolean)
    fun getLoginState(): Flow<Boolean>
}