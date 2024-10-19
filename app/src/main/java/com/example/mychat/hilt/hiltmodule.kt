package com.example.mychat.hilt


import com.example.mychat.data.DB.PreferencesDataStore
import com.example.mychat.data.repo.PreferenceRepo
import com.example.mychat.data.repoimpl.PreferenceRepoImpl
import com.example.mychat.domain.LoginStateUseCase
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
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
    fun provideAuth(): FirebaseAuth {
        return Firebase.auth
    }


}
