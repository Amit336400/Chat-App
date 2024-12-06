package com.example.mychat.presentation.newChatsScreen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mychat.domain.Ext.id
import com.example.mychat.presentation.navigation.Routs
import com.example.mychat.ui.comp.UserCard
import com.example.mychat.ui.comp.isLoading
import com.streamliners.base.taskState.comp.whenLoaded
import com.streamliners.base.taskState.comp.whenLoading
import com.streamliners.compose.android.comp.appBar.TitleBarScaffold

@Composable
fun NewChatScreen(
    chatViewModel: NewChatViewModel,
    navHostController: NavHostController,
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {
        chatViewModel.fetchUsers()
    }
    TitleBarScaffold(title = "New Chat") {

        chatViewModel.usersListTask.whenLoaded { userList ->
            LazyColumn(
                modifier = Modifier.padding(it),
                contentPadding = PaddingValues(10.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                items(userList) { user ->
                    UserCard(
                        user = user,
                        onClick = {
                            Toast.makeText(context, "Click", Toast.LENGTH_SHORT).show()
                            chatViewModel.onUserSelected(
                                otherUserId = user.id(),
                                onChannelReady = {
                                    navHostController.navigate(Routs.ChatScreen(it))
                                }
                            )
                        }
                    )
                }
            }
        }
        chatViewModel.usersListTask.whenLoading {
            isLoading(modifier = Modifier.fillMaxSize())
        }
    }
}

