package com.example.mychat.Test.work

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object obj {

    @Singleton
    @Provides
    fun provideWork (): Work {
        return Work()
    }


}