package com.example.mychat.presentation.navigation

import kotlinx.serialization.Serializable
import javax.inject.Singleton


sealed class SubNavigation {

    @Serializable
    object LoginUserScreen : SubNavigation()

    @Serializable
    object MainHomeScreen : SubNavigation()
}


@Singleton
class Routs {
    @Serializable
    object LoginScreenRout

    @Serializable
    object SaveUserDataRouts

    @Serializable
    object HomeScreenRout


}