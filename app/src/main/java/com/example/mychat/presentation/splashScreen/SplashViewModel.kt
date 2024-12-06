package com.example.mychat.presentation.splashScreen

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.mychat.data.localRepo.repo.PreferenceRepo
import com.example.mychat.presentation.navigation.Routes
import com.streamliners.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class SplashViewModel @Inject constructor(
    private val useCase: PreferenceRepo,
) : BaseViewModel() {

    fun checkUserIsLoginOrNot(
        navHostController: NavHostController,
    ) {
        viewModelScope.launch(
            Dispatchers.Default
        ) {
            val log = useCase.getLoginState()

            // TODO On error toast

            Log.d("CheckLog", "checkUserIsLoginOrNot: $log")

            withContext(Dispatchers.Main) {
                if (log){
                    navHostController.navigate(Routes.HomeScreen)
                }
                else{
                    navHostController.navigate(Routes.LoginScreen)
                }
            }
        }
    }
}