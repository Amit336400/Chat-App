package com.example.mychat.presentation.home
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel

class HomeViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
):ViewModel() {

    init {
        val currentUser = liveData {
            emit(firebaseAuth.currentUser)
        }
    }




}