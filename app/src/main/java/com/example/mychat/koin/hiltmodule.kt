package com.example.mychat.koin


/*
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
        @ApplicationContext context: Context
    ) : PreferenceRepo {
        return PreferenceRepoImpl(DataStoreUtil.create(context))
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
*/