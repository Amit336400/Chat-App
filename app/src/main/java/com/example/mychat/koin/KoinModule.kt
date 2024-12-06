package com.example.mychat.koin


import com.example.mychat.data.DB.DataStoreUtil
import com.example.mychat.domain.local.repo.PreferenceRepo
import com.example.mychat.data.local.repoimpl.PreferenceRepoImpl
import com.example.mychat.data.remote.ChannelRepoImpl
import com.example.mychat.domain.remote.UserRepo
import com.example.mychat.data.remote.UserRepoImpl
import com.example.mychat.domain.remote.ChannelRepo
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
import com.google.firebase.firestore.firestore
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    single { DataStoreUtil.create(get()) }
    single<PreferenceRepo> { PreferenceRepoImpl(get()) }
    single<FirebaseAuth> { Firebase.auth }
    single<FirebaseFirestore> { Firebase.firestore }
    single<UserRepo> { UserRepoImpl(get()) }
    single<ChannelRepo> { ChannelRepoImpl(get()) }


}

val viewModel = module {

    viewModel { EditProfileViewModel(get(),get()) }
    viewModel { LoginViewModel(get(),get()) }
    viewModel { NewChatViewModel(get (),get()) }
    viewModel { SplashViewModel(get ()) }
    viewModel { HomeViewModel(get (),get()) }
    viewModel { ChatViewModel(get ()) }

}


