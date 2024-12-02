package com.example.mychat.presentation.home
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.mychat.data.remoteRepo.RemoteRepo
import com.google.firebase.auth.FirebaseAuth
import com.streamliners.base.BaseViewModel
import javax.inject.Inject



class HomeViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
):BaseViewModel() {

    init {
        val currentUser = liveData {
            emit(firebaseAuth.currentUser)
        }
    }




}