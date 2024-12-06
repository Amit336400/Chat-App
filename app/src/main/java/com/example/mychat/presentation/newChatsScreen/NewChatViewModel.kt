package com.example.mychat.presentation.newChatsScreen

import com.example.mychat.domain.remote.UserRepo
import com.example.mychat.domain.Ext.currentUser
import com.example.mychat.domain.Ext.id
import com.example.mychat.domain.model.User
import com.example.mychat.domain.remote.ChannelRepo
import com.streamliners.base.BaseViewModel
import com.streamliners.base.ext.execute
import com.streamliners.base.ext.executeOnMain
import com.streamliners.base.taskState.load
import com.streamliners.base.taskState.taskStateOf
import javax.inject.Inject


class NewChatViewModel @Inject constructor(
    private val channelRepo: ChannelRepo,
    private val userRepo: UserRepo,
) : BaseViewModel() {

    val usersListTask = taskStateOf<List<User>>()

    fun fetchUsers() {
        execute(showLoadingDialog = false) {
            usersListTask.load {
                userRepo.getAllUser().filter { it.id() != currentUser() }
            }
        }
    }

    fun onUserSelected(
        otherUserId: String,
        onChannelReady: (String) -> Unit) {

        execute(showLoadingDialog = false) {

            val channel = channelRepo.getOneToOneChat(currentUser(), otherUserId)
            val channelId =  if (channel != null) {
                channel.id()
            } else {
                channelRepo.createOneToOneChannel(currentUser(),otherUserId)
            }
            executeOnMain {
                onChannelReady(channelId)
            }
        }
    }
}


