package com.example.mychat.hilt


import com.example.mychat.data.DB.PreferencesDataStore
import com.example.mychat.data.localRepo.repo.PreferenceRepo
import com.example.mychat.data.localRepo.repoimpl.PreferenceRepoImpl
import com.example.mychat.data.remoteRepo.RemoteRepo
import com.example.mychat.data.remoteRepo.RemoteRepoImpl
import com.example.mychat.domain.usecase.LoginStateUseCase
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module that provides dependencies for repository and other layers.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePrefUseCaseDB(
       preferenceRepo: PreferenceRepo
    ): LoginStateUseCase {
        return LoginStateUseCase(preferenceRepo)
    }

    @Singleton
    @Provides
    fun providePreferencesRepo (
        dataStore: PreferencesDataStore
    ) : PreferenceRepo {
        return PreferenceRepoImpl(dataStore)
    }
    @Singleton
    @Provides
    fun provideUserRepo(
        firestore: FirebaseFirestore,
    ): RemoteRepo {
        return RemoteRepoImpl(firestore = firestore)
    }

    @Singleton
    @Provides
    fun provideAuth(): FirebaseAuth {
        return Firebase.auth
    }

    @Singleton
    @Provides
    fun provideFirebaseFireStore(): FirebaseFirestore {
        return Firebase.firestore
    }


}
