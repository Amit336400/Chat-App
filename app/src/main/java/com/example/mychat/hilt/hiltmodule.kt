package com.example.mychat.hilt


import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Hilt module that provides dependencies for repository and other layers.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {


}
