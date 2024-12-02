package com.example.mychat.presentation.newChatsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mychat.data.remoteRepo.RemoteRepo
import com.example.mychat.domain.Ext.currentUser
import com.example.mychat.domain.Ext.id
import com.example.mychat.domain.model.User
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.streamliners.base.BaseViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class NewChatViewModel @Inject constructor(
    private val repo: RemoteRepo,
) : BaseViewModel() {

    val currentUser = Firebase.auth

    private val _uiState = MutableStateFlow<ChatUiState>(ChatUiState.Loading)
    val uiState: StateFlow<ChatUiState> = _uiState



     fun fetchUsers() {
        val exception = CoroutineExceptionHandler { _, error ->
            _uiState.value = ChatUiState.Error(message = error.localizedMessage.toString())
        }
        viewModelScope.launch(Dispatchers.IO + exception) {
            repo.getAllUser()
                .onStart { _uiState.value = ChatUiState.Loading }
                .collect { result ->
                    _uiState.value =
                        ChatUiState.Success(users = result.filter { it.id() != currentUser() })
                }
        }
    }





    fun onUserSelected(
        otherUserId: String,
        onChannelReady: (String) -> Unit) {

        viewModelScope.launch(
            Dispatchers.IO
        ) {
            val channel = repo.getOneToOneChat(currentUser.uid!!, otherUserId)

            withContext(Dispatchers.Main) {
              val channelId = channel?.id() ?: repo.createOneToOneChannel(currentUser(),otherUserId)

                withContext(Dispatchers.Main){
                    onChannelReady(channelId)
                }
            }

        }


    }



}

// Define UI state sealed class
sealed class ChatUiState {
    object Loading : ChatUiState()
    data class Success(val users: List<User>) : ChatUiState()
    data class Error(val message: String) : ChatUiState()
}
