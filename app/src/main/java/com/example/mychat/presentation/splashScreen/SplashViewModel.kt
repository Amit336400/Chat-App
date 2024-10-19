package com.example.mychat.presentation.splashScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mychat.domain.LoginStateUseCase
import com.example.mychat.presentation.common.ResultState
import com.example.mychat.presentation.saveUserData.GetLoginState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val useCase: LoginStateUseCase,

    ) : ViewModel() {

    private val _isLogin = MutableStateFlow(GetLoginState())
    val isLogin= _isLogin.asStateFlow()

    fun getLoginState() {
        viewModelScope.launch(Dispatchers.IO) {
            // Collect the Flow from the use case and update LiveData
            useCase.getLoginState().collect {
                when(it){
                    is ResultState.Error -> {
                        _isLogin.value = _isLogin.value.copy(
                            error =it.error,
                            isLoading = false
                        )
                    }
                    ResultState.IsLoading ->{
                        _isLogin.value = _isLogin.value.copy(
                            isLoading = true
                        )
                    }
                    is ResultState.Success -> {
                        _isLogin.value = _isLogin.value.copy(
                            data = it.data,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

}