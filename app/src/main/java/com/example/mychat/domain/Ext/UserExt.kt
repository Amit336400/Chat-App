package com.example.mychat.domain.Ext

import com.example.mychat.domain.model.User
import com.example.mychat.ui.comp.placeHolder

fun User.id() : String{
    return id ?: error("Id Not Found")
}

fun User.imageUri () : String{
    return imageUri ?: placeHolder(name)
}