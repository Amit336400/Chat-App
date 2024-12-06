package com.example.mychat

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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

                UiEventDialogs()
                NavHostGraph()

            }
        }
    }
}

