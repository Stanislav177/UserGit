package com.example.usergit

import android.app.Application
import android.content.Context
import com.example.usergit.di.customDi.appModules
import org.koin.core.context.startKoin

class App : Application() {

    /**
     * Инициализация Custom DI
     */
    init {
        appModules.initContext(this)
        appModules.install()

    }

    /**
     * Инициализация dagger2
     * Оставил комментарий для себя чтобы не забыть
     */
//    val appComponent: AppComponent by lazy {
//        DaggerAppComponent.builder().appModulesContext(AppModulesContext(this)).build()
//    }
}

val Context.app: App get() = applicationContext as App