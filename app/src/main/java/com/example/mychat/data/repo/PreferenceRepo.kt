package com.example.mychat.data.repo

import com.example.mychat.presentation.common.ResultState
import kotlinx.coroutines.flow.Flow

interface PreferenceRepo {
    suspend fun getLoginState() : Flow<ResultState<Boolean>>
    suspend fun saveLoginState(isLogin : Boolean) : Flow<ResultState<String>>
}