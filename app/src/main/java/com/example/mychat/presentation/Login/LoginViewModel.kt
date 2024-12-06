package com.example.mychat.presentation.Login

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.mychat.domain.local.repo.PreferenceRepo
import com.example.mychat.domain.remote.UserRepo
import com.example.mychat.presentation.navigation.Routes
import com.streamliners.base.BaseViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject


class LoginViewModel @Inject constructor(
    val repo: UserRepo,
    val preferenceRepo: PreferenceRepo
) : BaseViewModel() {

    /**
     *  check user is login before or not if login the go to home screen
     *  or Edit Screen
     */

    fun loginBefore(
        email: String,
        navHostController: NavHostController,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch(CoroutineExceptionHandler { _, error ->
            error.localizedMessage?.let(onError)
        }) {
            val user = repo.getUserWithEmail(email)
            if (user != null) {
                preferenceRepo.saveLoginState(true) // Save login state
                navHostController.navigate(Routes.HomeScreen)
            } else {
                navHostController.navigate(Routes.EditProfileScreen)
            }
        }
    }
}