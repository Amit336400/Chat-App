package com.example.mychat.domain.usecase

import com.example.mychat.data.localRepo.repo.PreferenceRepo
import com.example.mychat.presentation.common.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class LoginStateUseCase @Inject constructor(
    private val preferenceRepo: PreferenceRepo
) {
   suspend fun saveLoginState(isLogin : Boolean)  {
       return preferenceRepo.saveLoginState(isLogin)
    }

    suspend fun getLoginState() : Boolean {
        return preferenceRepo.getLoginState()

    }

}