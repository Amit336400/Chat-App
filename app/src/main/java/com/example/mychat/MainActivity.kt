package com.example.mychat

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.mychat.presentation.navigation.NavHostGraph
import com.example.mychat.ui.theme.MyChatTheme
import com.streamliners.base.BaseActivity
import com.streamliners.base.uiEvent.UiEventDialogs


class MainActivity: BaseActivity(){

 override var buildType: String = BuildConfig.BUILD_TYPE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       enableEdgeToEdge()
        setContent {
            MyChatTheme {
                 //    Main content of the app, such as chat screen

                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        UiEventDialogs()
                        Box(modifier = Modifier.padding(innerPadding)){
                           NavHostGraph()

                        }

                    }


            }
        }
    }
}
