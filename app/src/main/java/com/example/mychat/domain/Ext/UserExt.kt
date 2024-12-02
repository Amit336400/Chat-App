package com.example.mychat.domain.Ext

import com.example.mychat.domain.model.User

fun User.id() : String{
    return id ?: error("Id Not Found")
}