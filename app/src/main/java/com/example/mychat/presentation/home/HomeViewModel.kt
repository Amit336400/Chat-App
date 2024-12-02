package com.example.mychat.presentation.home
import com.example.mychat.data.remoteRepo.RemoteRepo
import com.example.mychat.domain.Ext.currentUser
import com.example.mychat.domain.model.Channel
import com.streamliners.base.BaseViewModel
import com.streamliners.base.ext.execute
import com.streamliners.base.taskState.load
import com.streamliners.base.taskState.taskStateOf


class HomeViewModel(
    private val repo: RemoteRepo,
):BaseViewModel() {

    val channels = taskStateOf<List<Channel>>()

    fun start() {
        execute(showLoadingDialog = false) {
            channels.load {
                repo.getAllChannels(currentUser = currentUser())
            }

        }

    }


}