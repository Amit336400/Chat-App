package com.example.mychat.presentation.navigation

import kotlinx.serialization.Serializable
import javax.inject.Singleton



@Serializable
open class Routs {
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