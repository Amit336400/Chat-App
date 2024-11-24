package com.example.mychat.data.localRepo.repo

import com.example.mychat.presentation.common.ResultState
import kotlinx.coroutines.flow.Flow

interface PreferenceRepo {
    suspend fun getLoginState() : Boolean
    suspend fun saveLoginState(isLogin : Boolean)
}