package com.example.mychat.data.localRepo.repo

import kotlinx.coroutines.flow.Flow

interface PreferenceRepo {
    suspend fun getLoginState() : Boolean
    suspend fun saveLoginState(isLogin : Boolean)
}