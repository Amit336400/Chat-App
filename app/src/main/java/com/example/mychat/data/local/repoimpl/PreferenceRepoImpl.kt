package com.example.mychat.data.local.repoimpl

import com.example.mychat.data.DB.DataStoreUtil
import com.example.mychat.domain.local.repo.PreferenceRepo
import javax.inject.Inject

class PreferenceRepoImpl @Inject constructor(
    private  val dataStore:DataStoreUtil
) : PreferenceRepo {
    override suspend fun getLoginState(): Boolean {
        return dataStore.getData<Boolean>(LOGIN_KEY) ?: false
    }

    override suspend fun saveLoginState(isLogin: Boolean) {
        dataStore.setData(LOGIN_KEY,isLogin)
    }

}




