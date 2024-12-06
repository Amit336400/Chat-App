package com.example.mychat.presentation.chatSceen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.mychat.ui.comp.isLoading
import com.streamliners.base.taskState.comp.whenLoaded
import com.streamliners.base.taskState.comp.whenLoading
import com.streamliners.compose.android.comp.appBar.TitleBarScaffold
import com.streamliners.compose.comp.textInput.TextInputLayout
import com.streamliners.compose.comp.textInput.state.TextInputState
import com.streamliners.compose.comp.textInput.state.ifValidInput
import com.streamliners.compose.comp.textInput.state.update

@Composable
fun ChatScreen(
    navHostController: NavHostController,
    channelId: String,
    viewModel: ChatViewModel,
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.start(channelId)
    }

    TitleBarScaffold(title = "Chat") {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            val massageInput = remember {
                mutableStateOf(
                    TextInputState("Massage")
                )
            }
            Column(
                modifier = Modifier.weight(1f)
            ) {
                viewModel.channel.whenLoading {
                    isLoading(modifier = Modifier.fillMaxSize())
                }
                viewModel.channel.whenLoaded {
                    MassageList(it)
                }
            }
            TextInputLayout(
                state = massageInput,
                trailingIconButton = {
                    IconButton(onClick = {
                        massageInput.ifValidInput { massage->
                            viewModel.sendMassage(massage,channelId = channelId, onSuccess = {
                                massageInput.update("")
                            })
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Send, contentDescription = null)
                    }
                }
            )
        }
    }
}