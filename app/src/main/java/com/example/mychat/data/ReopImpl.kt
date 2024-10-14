package com.example.mychat.data

import kotlinx.coroutines.flow.Flow

class RepoImpl(
    private val dataStore: PreferencesDataStore
) : ChatRepo{
    override suspend fun saveLoginState(isLoggedIn: Boolean) = dataStore.saveLoginState(isLoggedIn)

    override fun getLoginState(): Flow<Boolean> = dataStore.getLoginState()

}