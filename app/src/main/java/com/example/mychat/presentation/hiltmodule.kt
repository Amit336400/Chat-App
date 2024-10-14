package com.example.mychat.presentation

import com.example.mychat.data.PreferencesDataStore
import com.example.mychat.data.ChatRepo
import com.example.mychat.data.RepoImpl
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

    @Provides
    @Singleton
    fun providePreferencesRepository(
        dataStore: PreferencesDataStore
    ) : ChatRepo{
        return RepoImpl(dataStore)
    }
}
