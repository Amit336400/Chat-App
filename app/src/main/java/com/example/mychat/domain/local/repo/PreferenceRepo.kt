package com.example.mychat.domain.local.repo

interface PreferenceRepo {
    suspend fun getLoginState() : Boolean
    suspend fun saveLoginState(isLogin : Boolean)
}