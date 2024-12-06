package com.example.mychatapp.domain.Ext

import com.example.mychatapp.domain.model.Channel


fun Channel.id() : String{
    return id ?: error("Channel Not Found")
}