package com.example.usergit

import android.app.Application
import android.content.Context
import com.example.usergit.di.appNetworkModule
import com.example.usergit.di.appRoomModule
import com.example.usergit.di.appViewModels
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appNetworkModule, appRoomModule, appViewModels)
        }
    }
}

val Context.app: App get() = applicationContext as App