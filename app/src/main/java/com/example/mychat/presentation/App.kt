package com.example.mychat.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.mychat.presentation.navigation.NavHostGraph

import com.example.mychat.presentation.EditProfile.EditProfileViewModel
import com.example.mychat.presentation.splashScreen.SplashScreen
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun App(
    navHostController: NavHostController,
    saveUserDataViewModel: EditProfileViewModel
) {


    //saveUserDataViewModel.getLoginState()
    val auth = Firebase.auth



}
