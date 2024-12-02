package com.example.mychat.presentation.newChatsScreen

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.mychat.domain.Ext.id
import com.example.mychat.presentation.navigation.Routs
import com.example.mychat.ui.comp.UserCard

@Composable
fun NewChatScreen(
    chatViewModel: NewChatViewModel,
    navHostController: NavHostController,
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {
        chatViewModel.fetchUsers()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        val data by chatViewModel.uiState.collectAsStateWithLifecycle()
        when (data) {
            is ChatUiState.Error -> {
                Toast.makeText(
                    context,
                    "${(data as ChatUiState.Error).message}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            ChatUiState.Loading -> {
                isLoading(modifier = Modifier.fillMaxSize())
            }

            is ChatUiState.Success -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items((data as ChatUiState.Success).users) { user ->
                        UserCard(
                            user = user,
                            onClick = {
                                navHostController.navigate(Routs.ChatScreen(user.id()))
                            }
                        )
                    }
                }
            }
        }
    }


}
@Composable
fun isLoading(modifier: Modifier) {
    Box(modifier = modifier) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    }

}

