package com.example.mychat.presentation.Login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mychat.data.ChatRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repo: ChatRepo,
) : ViewModel() {

    private val _isLogin = MutableStateFlow(false)
    val isLogin = _isLogin

    init {
        checkLogin()
    }



    private fun checkLogin() {
        viewModelScope.launch {
            repo.getLoginState().collect {
                _isLogin.value = it
            }
        }

    }
    fun saveDataInPre(data : Boolean){
        viewModelScope.launch {
            repo.saveLoginState(data)
        }
    }
}