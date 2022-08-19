package com.example.usergit.di.dagger

import android.content.Context
import dagger.Module
import dagger.Provides


@Module
class AppModulesContext(var context: Context) {

    @Provides
    fun provideContext(): Context {
        return context.applicationContext
    }
}