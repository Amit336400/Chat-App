package com.example.mychat.presentation.navigation

import kotlinx.serialization.Serializable
import javax.inject.Singleton



@Serializable
object Routs {
    @Serializable
    object LoginScreenRout

    @Serializable
    object EditProfileRouts

    @Serializable
    object HomeScreenRout

    @Serializable
    object SplashScreen

    @Serializable
    object NewChatScreen

    @Serializable
    data class ChatScreen(val channelId : String)

}