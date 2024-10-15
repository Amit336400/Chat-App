package com.example.googleauth.data

// AuthData.kt
data class SignInResult(
    val data: UserData? = null,
    val success: Boolean = false,
    val errorMessage: String? = null,
)

data class UserData(
    val userId: String? = null,
    val displayName: String? = null,
    val userPic: String? = null,
    val email: String? = null,
)
