package com.example.mychat.presentation.splashScreen

import androidx.navigation.NavHostController
import com.example.mychat.data.localRepo.repo.PreferenceRepo
import com.example.mychat.presentation.navigation.Routes
import com.streamliners.base.BaseViewModel
import com.streamliners.base.ext.execute
import com.streamliners.base.taskState.load
import com.streamliners.base.taskState.taskStateOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class SplashViewModel @Inject constructor(
    private val repo: PreferenceRepo,
) : BaseViewModel() {

    val taskState = taskStateOf<Unit>()

    fun checkUserIsLoginOrNot(
        navHostController: NavHostController,
    ) {
        execute(showLoadingDialog = false) {
            taskState.load {

                val state = repo.getLoginState()

                withContext(Dispatchers.Main) {
                    if (state){
                        navHostController.navigate(Routes.HomeScreen)
                    }
                    else{
                        navHostController.navigate(Routes.LoginScreen)
                    }
                }
            }
        }
    }
}