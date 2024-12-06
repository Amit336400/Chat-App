package com.example.mychat.presentation.home
import android.util.Log
import com.example.mychat.data.remoteRepo.RemoteRepo
import com.example.mychat.domain.Ext.currentUser
import com.example.mychat.domain.Ext.id
import com.example.mychat.domain.Ext.imageUri
import com.example.mychat.domain.model.Channel
import com.streamliners.base.BaseViewModel
import com.streamliners.base.ext.execute
import com.streamliners.base.taskState.taskStateOf
import com.streamliners.base.taskState.update
import javax.inject.Inject


class HomeViewModel @Inject constructor(
    private val repo: RemoteRepo,
):BaseViewModel() {

    val channelsState = taskStateOf<List<Channel>>()

    fun start() {
        execute(showLoadingDialog = false) {
            val users = repo.getAllUser()
            val channels = repo.getAllChannels(currentUser = currentUser())
                .map { channel ->
                    if (channel.type == Channel.Type.OneToOne) {

                        val otherUserId = channel.members.find { it != currentUser() }
                            ?: error("Other User Id Not Found")

                        val otherUser = users.find {
                            it.id() == otherUserId
                        } ?: error("User with Id $otherUserId Not Found")

                        channel.copy(
                            name = otherUser.name,
                            imageUrl = otherUser.imageUri()
                        )
                    } else {
                        channel
                    }
                }
            channelsState.update(channels)
        }
    }
}


