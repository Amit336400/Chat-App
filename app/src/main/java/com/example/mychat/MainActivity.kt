package com.example.mychat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.mychat.presentation.App
import com.example.mychat.presentation.Login.LoginScreen
import com.example.mychat.presentation.home.Home
import com.example.mychat.presentation.splashScreen.CustomSplashScreen
import com.example.mychat.ui.theme.MyChatTheme
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       enableEdgeToEdge()
        setContent {
            MyChatTheme {
                val navController = rememberNavController()

                val auth = FirebaseAuth.getInstance()

                var showSplash by remember { mutableStateOf(true) }

                if (showSplash) {
                    CustomSplashScreen {
                        showSplash = false
                    }
                }
                else {
                    // Main content of the app, such as chat screen
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        Box(modifier = Modifier.padding(innerPadding)){
                            App(navHostController =navController)
                        }

                    }
                }



            }
        }
    }
}
