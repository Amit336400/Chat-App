package com.example.mychat.presentation.saveUserData

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.mychat.domain.LoginStateUseCase
import com.example.mychat.presentation.common.ResultState
import com.example.mychat.presentation.navigation.Routs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SaveUserDataViewModel @Inject constructor(
private val useCase: LoginStateUseCase,
) : ViewModel() {

    /**
     * MutableLiveData to hold the current login state of the user.
     * It is private to ensure that only the ViewModel can modify the value.
     */


    private val _isLoginSave = MutableStateFlow(GetLoginState())
    val loginSave= _isLoginSave.asStateFlow()


    fun saveLoginState(isLoggedIn: Boolean,navHostController: NavHostController) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.saveLoginState(isLoggedIn).collect{
                when(it){
                    is ResultState.Error -> {
                        _isLoginSave.value = _isLoginSave.value.copy(
                            isLoading = false,
                            error = it.error
                        )
                    }
                    ResultState.IsLoading -> {
                        _isLoginSave.value = _isLoginSave.value.copy(
                            isLoading = true
                        )
                    }
                    is ResultState.Success -> {
                        _isLoginSave.value = _isLoginSave.value.copy(
                            isLoading = false,
                        )
                    }
                }
            }
        }
    }
}


data class GetLoginState(
    val isLoading : Boolean = false,
    val error : String ="",
    val data : Boolean = false
)