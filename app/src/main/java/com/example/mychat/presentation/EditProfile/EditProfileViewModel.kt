package com.example.mychat.presentation.EditProfile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mychat.data.remoteRepo.RemoteRepo
import com.example.mychat.domain.usecase.LoginStateUseCase
import com.example.mychat.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val useCase: LoginStateUseCase,
    private val userRepo: RemoteRepo,
) : ViewModel() {

    /**
     * MutableLiveData to hold the current login state of the user.
     * It is private to ensure that only the ViewModel can modify the value.
     */


    fun saveUser(user: User,onSuccess : () -> Unit) {
        viewModelScope.launch(
            Dispatchers.IO
        ) {
            userRepo.saveUserData(user = user)
            useCase.saveLoginState(true)
            withContext(Dispatchers.Main) {
                onSuccess()
            }
        }
    }
}
