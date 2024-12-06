package com.example.mychatapp.domain.Ext

import com.google.firebase.Firebase
import com.google.firebase.auth.auth


fun currentUser() : String {
    return  Firebase.auth.currentUser?.uid ?: error("Current User Not Found")
}