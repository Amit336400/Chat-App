package com.example.mychatapp.domain.ext

import com.example.mychatapp.domain.model.User
import com.example.mychatapp.ui.comp.placeHolder

fun User.id() : String{
    return id ?: error("Id Not Found")
}

fun User.imageUri () : String{
    return this.imageUri ?: placeHolder(name)
}