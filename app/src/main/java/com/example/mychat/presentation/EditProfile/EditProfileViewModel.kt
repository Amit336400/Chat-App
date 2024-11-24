package com.example.mychat.presentation.EditProfile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mychat.data.remoteRepo.RemoteRepo
import com.example.mychat.domain.model.User
import com.example.mychat.domain.usecase.LoginStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val useCase: LoginStateUseCase,
    private val userRepo: RemoteRepo,
) : ViewModel() {

    fun saveUser(
        user: User,
        onSuccess: () -> Unit,
        onError: (String) -> Unit,
    ) {
        val exceptionHandler =
            CoroutineExceptionHandler { _, error ->
                error.localizedMessage?.let { onError(it) }
            }
        viewModelScope.launch(
            Dispatchers.IO + exceptionHandler
        ) {
            userRepo.saveUserData(user = user)
            useCase.saveLoginState(true)
            withContext(Dispatchers.Main) {
                onSuccess()
            }
        }
    }
}
