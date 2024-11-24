package com.example.mychat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.mychat.Test.TestUi
import com.example.mychat.presentation.navigation.NavHostGraph
import com.example.mychat.presentation.navigation.Routs
import com.example.mychat.ui.theme.MyChatTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       enableEdgeToEdge()
        setContent {
            MyChatTheme {
                val navController = rememberNavController()

                    // Main content of the app, such as chat screen
//                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                        Box(modifier = Modifier.padding(innerPadding)){
//                           NavHostGraph(navHostController = navController)
//                        }
//
//                    }

                TestUi()



            }
        }
    }
}
