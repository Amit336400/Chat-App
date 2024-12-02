package com.example.mychat.presentation.newChatsScreen

import android.util.Log
import com.example.mychat.data.remoteRepo.RemoteRepo
import com.example.mychat.domain.Ext.currentUser
import com.example.mychat.domain.Ext.id
import com.example.mychat.domain.model.User
import com.streamliners.base.BaseViewModel
import com.streamliners.base.ext.execute
import com.streamliners.base.ext.executeOnMain
import com.streamliners.base.taskState.load
import com.streamliners.base.taskState.taskStateOf
import javax.inject.Inject


class NewChatViewModel @Inject constructor(
    private val repo: RemoteRepo,
) : BaseViewModel() {

    val usersListTask = taskStateOf<List<User>>()


     fun fetchUsers() {
         execute(showLoadingDialog = false) {
             usersListTask.load {
                 repo.getAllUser().filter { it.id() != currentUser() }
             }
         }
    }





    fun onUserSelected(
        otherUserId: String,
        onChannelReady: (String) -> Unit) {

        execute(showLoadingDialog = false) {

            val channel = repo.getOneToOneChat(currentUser(), otherUserId)
           val channelId =  if (channel != null) {
               channel.id()
            } else {
                repo.createOneToOneChannel(currentUser(),otherUserId)
            }
            executeOnMain {
                onChannelReady(channelId)
            }
        }


    }



}


