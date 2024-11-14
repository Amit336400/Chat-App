package com.example.mychat.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.mychat.presentation.navigation.NavHostGraph
import com.example.mychat.presentation.navigation.SubNavigation
import com.example.mychat.presentation.saveUserData.SaveUserDataViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun App(
    navHostController: NavHostController,
    saveUserDataViewModel: SaveUserDataViewModel = hiltViewModel(),
) {
    //saveUserDataViewModel.getLoginState()
    val isLoagin = saveUserDataViewModel.loginSave.collectAsStateWithLifecycle()
    val auth = Firebase.auth

    val startingDestination = if (isLoagin.value.data) {

        SubNavigation.MainHomeScreen

    } else {
        SubNavigation.LoginUserScreen
    }

    Scaffold {
        it
        NavHostGraph(
            navHostController = navHostController,
            startingDestination = startingDestination
        )
    }


}
