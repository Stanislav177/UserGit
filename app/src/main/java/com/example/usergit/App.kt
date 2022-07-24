package com.example.usergit

import android.app.Application
import android.content.Context
import com.example.usergit.di.dagger.AppComponent
import com.example.usergit.di.dagger.AppModulesContext
import com.example.usergit.di.dagger.DaggerAppComponent

class App : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().appModulesContext(AppModulesContext(this)).build()
    }
}

val Context.app: App get() = applicationContext as App
