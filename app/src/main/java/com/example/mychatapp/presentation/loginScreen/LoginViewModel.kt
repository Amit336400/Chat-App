package com.example.mychatapp.presentation.loginScreen

import androidx.navigation.NavHostController
import com.example.mychatapp.domain.local.repo.PreferenceRepo
import com.example.mychatapp.domain.remote.UserRepo
import com.example.mychatapp.presentation.navigation.Routes
import com.streamliners.base.BaseViewModel
import com.streamliners.base.ext.execute
import com.streamliners.base.ext.executeOnMain
import com.streamliners.base.taskState.load
import com.streamliners.base.taskState.taskStateOf
import javax.inject.Inject


class LoginViewModel @Inject constructor(
    val repo: UserRepo,
    val preferenceRepo: PreferenceRepo
) : BaseViewModel() {

    /**
     *  check user is login before or not if login the go to home screen
     *  or Edit Screen
     */
    val taskState = taskStateOf<Unit>()

    fun checkIfUserExistsAndLogin(
        email: String,
        navHostController: NavHostController,
    ) {
        execute(showLoadingDialog = false) {
            taskState.load {
                val user = repo.getUserWithEmail(email)

                executeOnMain {

                    if (user != null) {
                        preferenceRepo.saveLoginState(true) // Save login state
                        navHostController.navigate(Routes.HomeScreen)
                    } else {
                        navHostController.navigate(Routes.EditProfileScreen)
                    }

            }
            }
        }
    }
}