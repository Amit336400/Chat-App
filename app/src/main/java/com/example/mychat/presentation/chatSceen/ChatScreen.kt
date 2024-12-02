package com.example.mychat.presentation.chatSceen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.streamliners.compose.android.comp.appBar.TitleBarScaffold

@Composable
fun ChatScreen(navHostController: NavHostController,
               channelId : String) {
    
    TitleBarScaffold(title = "Chat") {
        
    }

}