package com.example.mychat.presentation.EditProfile

import com.example.mychat.data.localRepo.repo.PreferenceRepo
import com.example.mychat.data.remoteRepo.RemoteRepo
import com.example.mychat.domain.model.User
import com.streamliners.base.BaseViewModel
import com.streamliners.base.ext.execute
import com.streamliners.base.taskState.load
import com.streamliners.base.taskState.taskStateOf
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class EditProfileViewModel @Inject constructor(
    private val preferenceRepo: PreferenceRepo,
    private val userRepo: RemoteRepo,
) : BaseViewModel() {

    val saveProfileTask = taskStateOf<Unit>()


    fun saveUser(
        user: User,
        onSuccess: () -> Unit,
        onError: (String) -> Unit,
    ) {
        val exceptionHandler =
            CoroutineExceptionHandler { _, error ->
                error.localizedMessage?.let { onError(it) }
            }

        execute(showLoadingDialog = false) {
            saveProfileTask.load {
            userRepo.saveUserData(user = user)
                preferenceRepo.saveLoginState(true)
            withContext(Dispatchers.Main) {
                onSuccess()
            }
        }

    }
    }
}
