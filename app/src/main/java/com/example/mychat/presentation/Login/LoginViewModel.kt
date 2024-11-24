package com.example.mychat.presentation.Login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.mychat.data.remoteRepo.RemoteRepo
import com.example.mychat.domain.usecase.LoginStateUseCase
import com.example.mychat.presentation.navigation.Routs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val repo: RemoteRepo,
    val useCase: LoginStateUseCase
) : ViewModel() {

    /**
     *  check user is login before or not if login the go to home screen
     *  or Edit Screen
     */

    fun loginBefore(email : String,navHostController: NavHostController){
        viewModelScope.launch (Dispatchers.IO){
           val user =  repo.getUserWithEmail(email)
            withContext(Dispatchers.Main){
                if (user != null) {
                    // Save Local And Go Home Screen
                    useCase.saveLoginState(true)
                    navHostController.navigate(Routs.HomeScreenRout)
                }else{
                    // navigate EditProfile Screen
                    navHostController.navigate(Routs.EditProfileRouts)
                }
            }
            
        }

    }
}