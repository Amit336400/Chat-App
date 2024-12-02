package com.example.mychat.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.mychat.presentation.EditProfile.EditProfileScreen
import com.example.mychat.presentation.Login.LoginScreen
import com.example.mychat.presentation.chatSceen.ChatScreen
import com.example.mychat.presentation.home.Home
import com.example.mychat.presentation.newChatsScreen.NewChatScreen
import com.example.mychat.presentation.splashScreen.SplashScreen
import com.streamliners.base.BaseActivity
import com.streamliners.base.ext.hiltBaseViewModel
import com.streamliners.base.ext.koinBaseViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun BaseActivity.NavHostGraph() {

val navHostController = rememberNavController()

    NavHost(navController = navHostController, startDestination = Routs.SplashScreen){

        composable<Routs.SplashScreen> {
          SplashScreen(navHostController, viewModel = koinBaseViewModel())
        }

            composable<Routs.LoginScreenRout> {
                LoginScreen(navHostController, viewModel = koinBaseViewModel())
            }
            composable<Routs.EditProfileRouts> {
                EditProfileScreen(navHostController, viewModel = koinBaseViewModel() )
            }

            composable<Routs.HomeScreenRout> {
                Home(navHostController, viewModel = koinBaseViewModel())
            }

        composable<Routs.NewChatScreen> {

            NewChatScreen(navHostController = navHostController, chatViewModel = koinBaseViewModel())
        }
        composable<Routs.ChatScreen> {
            val channelId: Routs.ChatScreen = it.toRoute()
            ChatScreen(navHostController = navHostController, channelId = channelId.channelId)

        }


    }


}