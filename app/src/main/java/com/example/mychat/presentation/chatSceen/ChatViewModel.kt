package com.example.mychat.presentation.chatSceen

import com.example.mychat.data.remoteRepo.RemoteRepo
import com.example.mychat.domain.Ext.currentUser
import com.example.mychat.domain.model.Channel
import com.example.mychat.domain.model.Message
import com.streamliners.base.BaseViewModel
import com.streamliners.base.ext.execute
import com.streamliners.base.taskState.taskStateOf
import com.streamliners.base.taskState.update
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ChatViewModel(
    val repo: RemoteRepo,
) : BaseViewModel() {
    val channel = taskStateOf<Channel>()

    fun start(channelId: String) {
        execute(
            showLoadingDialog = false
        ) {
            launch {
                repo.getChannelWithFlowMessage(channelId).collectLatest {
                    channel.update(repo.getChannel(channelId))
                }
            }

        }

    }

    fun sendMassage(messageStr: String, channelId: String,onSuccess :() ->Unit) {
        val message = Message(
            message = messageStr,
            sender = currentUser(),
            mediaUrl = null
        )

        execute(showLoadingDialog = false) {
            repo.sendMassage(channelId = channelId, message =message)
            onSuccess()
        }
    }
}