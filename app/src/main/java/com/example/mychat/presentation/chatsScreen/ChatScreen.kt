package com.example.mychat.presentation.chatsScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun ChatScreen(
    chatViewModel: ChatViewModel = hiltViewModel(),
    navHostController: NavHostController,
) {

    LaunchedEffect(key1 = Unit) {
        chatViewModel.start()
    }


}