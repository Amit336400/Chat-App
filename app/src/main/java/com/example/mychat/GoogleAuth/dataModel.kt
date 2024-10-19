package com.example.googleauth.data

// AuthData.kt
data class SignInResult(
    val success: Boolean = false,
    val errorMessage: String? = null,
)

