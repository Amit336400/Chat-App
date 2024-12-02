package com.example.mychat

import android.app.Application
import com.example.mychat.koin.appModule
import com.example.mychat.koin.viewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class ChatApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@ChatApp)
            modules(listOf(appModule , viewModel))
        }
    }
}