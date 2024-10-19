package com.example.mychat.domain

import com.example.mychat.data.repo.PreferenceRepo
import com.example.mychat.presentation.common.ResultState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginStateUseCase @Inject constructor(
    private val preferenceRepo: PreferenceRepo
) {
   suspend fun saveLoginState(isLogin : Boolean) : Flow<ResultState<String>> {
       return preferenceRepo.saveLoginState(isLogin)
    }

    suspend fun getLoginState() : Flow<ResultState<Boolean>> {
       return preferenceRepo.getLoginState()
    }

}