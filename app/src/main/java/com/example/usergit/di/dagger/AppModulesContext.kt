package com.example.usergit.di.dagger

import android.content.Context
import dagger.Module
import dagger.Provides


@Module
class AppModulesContext(context: Context) {
    var context: Context = context


    @Provides
    fun provideContext(): Context {
        return context.applicationContext
    }
}