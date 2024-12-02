package com.example.mychat.domain.Ext

import com.example.mychat.domain.model.Channel


fun Channel.id() : String{
    return id ?: error("Channel Not Found")
}