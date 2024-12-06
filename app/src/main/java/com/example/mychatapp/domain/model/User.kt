package com.example.mychatapp.domain.model


data class User(
    val id: String? = null,
    val name: String,
    val email: String,
    val bio: String?,
    val gender: String?,
    val imageUri: String? = null
) {
    // Secondary constructor with specific default values
    constructor() : this(
        id = null,
        name = "",
        email = "",
        bio = "",
        gender = "",
        imageUri = null
    )
}
