package com.example.mychat.domain.model

data class User(
    val id: String? = null,
    val name: String = "",
    val email: String = "",
    val bio: String = "",
    val gender: String = "",
)