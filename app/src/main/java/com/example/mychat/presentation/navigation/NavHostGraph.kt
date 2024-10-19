package com.example.mychat.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.mychat.presentation.Login.LoginScreen
import com.example.mychat.presentation.home.Home
import com.example.mychat.presentation.saveUserData.SaveUserData

@Composable
fun NavHostGraph(navHostController: NavHostController , startingDestination : SubNavigation) {



    NavHost(navController = navHostController, startDestination = startingDestination){


        navigation<SubNavigation.LoginUserScreen>(startDestination = Routs.LoginScreenRout){
            composable<Routs.LoginScreenRout> {
                LoginScreen(navHostController)
            }
            composable<Routs.SaveUserDataRouts> {
                SaveUserData(navHostController)
            }
        }

        navigation<SubNavigation.MainHomeScreen>(startDestination = Routs.HomeScreenRout){
            composable<Routs.HomeScreenRout> {
                Home()
            }
        }

    }


}