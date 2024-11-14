package com.example.mychat.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mychat.presentation.Login.LoginScreen
import com.example.mychat.presentation.home.Home
import com.example.mychat.presentation.EditProfile.SaveUserData
import com.example.mychat.presentation.splashScreen.SplashScreen

@Composable
fun NavHostGraph(navHostController: NavHostController , startingDestination : SubNavigation) {



    NavHost(navController = navHostController, startDestination = Routs.SplashScreen){

        composable<Routs.SplashScreen> {
          SplashScreen(navHostController)
        }

            composable<Routs.LoginScreenRout> {
                LoginScreen(navHostController)
            }
            composable<Routs.EditProfileRouts> {
                SaveUserData(navHostController)
            }

            composable<Routs.HomeScreenRout> {
                Home()
            }


    }


}