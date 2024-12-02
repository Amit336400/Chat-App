package com.example.mychat.koin


import android.annotation.SuppressLint
import com.example.mychat.data.DB.DataStoreUtil
import com.example.mychat.data.localRepo.repo.PreferenceRepo
import com.example.mychat.data.localRepo.repoimpl.PreferenceRepoImpl
import com.example.mychat.data.remoteRepo.RemoteRepo
import com.example.mychat.data.remoteRepo.RemoteRepoImpl
import com.example.mychat.presentation.EditProfile.EditProfileViewModel
import com.example.mychat.presentation.Login.LoginViewModel
import com.example.mychat.presentation.chatSceen.ChatViewModel
import com.example.mychat.presentation.home.HomeViewModel
import com.example.mychat.presentation.newChatsScreen.NewChatViewModel
import com.example.mychat.presentation.splashScreen.SplashViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.core.ActivityScope.bind
import com.google.firebase.firestore.firestore
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val appModule = module {

    // Provide DataStoreUtil
    single { DataStoreUtil.create(get()) }

    // Provide PreferenceRepo implementation
    single<PreferenceRepo> { PreferenceRepoImpl(get()) }

    single<FirebaseAuth> { Firebase.auth }

    single<FirebaseFirestore> { Firebase.firestore }



    // Bind RemoteRepo to RemoteRepoImpl
    single<RemoteRepo> { RemoteRepoImpl(get()) }


}

val viewModel = module {
    viewModel { EditProfileViewModel(get(),get()) }
    viewModel { LoginViewModel(get(),get()) }
    viewModel { NewChatViewModel(get ()) }
    viewModel { SplashViewModel(get ()) }
    viewModel { HomeViewModel(get ()) }
    viewModel { ChatViewModel(get ()) }
}


