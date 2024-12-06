package com.example.mychatapp.domain.Ext

import com.example.mychatapp.domain.model.User
import com.example.mychatapp.ui.comp.placeHolder

fun User.id() : String{
    return id ?: error("Id Not Found")
}

fun User.imageUri () : String{
    return imageUri ?: placeHolder(name)
}