package com.example.mychatapp.presentation.splashScreen

import androidx.navigation.NavHostController
import com.example.mychatapp.domain.local.repo.PreferenceRepo
import com.example.mychatapp.presentation.navigation.Routes
import com.streamliners.base.BaseViewModel
import com.streamliners.base.ext.execute
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class SplashViewModel @Inject constructor(
    private val repo: PreferenceRepo,
) : BaseViewModel() {


    fun checkUserIsLoginOrNot(
        navHostController: NavHostController,
    ) {
        execute(showLoadingDialog = false) {

            val isLoggedIn = repo.getLoginState()

            withContext(Dispatchers.Main) {
                if (isLoggedIn){
                    navHostController.navigate(Routes.HomeScreen)
                }
                else{
                    navHostController.navigate(Routes.LoginScreen)
                }
            }
        }
    }
}