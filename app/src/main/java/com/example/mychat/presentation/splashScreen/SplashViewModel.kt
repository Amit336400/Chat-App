package com.example.mychat.presentation.splashScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.mychat.domain.usecase.LoginStateUseCase
import com.example.mychat.presentation.navigation.Routs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val useCase: LoginStateUseCase,
    ) : ViewModel() {

    fun checkUserIsLoginOrNot(
        navHostController: NavHostController,
    ) {
        viewModelScope.launch(
            Dispatchers.Default
        ) {
            val log = useCase.getLoginState()
            withContext(Dispatchers.Main) {
                if (log) {
                    navHostController.navigate(Routs.HomeScreenRout)
                } else {
                    navHostController.navigate(Routs.LoginScreenRout)
                }

            }

    }
    }
}