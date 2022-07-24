package com.example.usergit.di.dagger

import com.example.usergit.ui.detailingUser.DetailingUserActivity
import com.example.usergit.ui.listUsers.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [
    AppModulesUser::class,
    AppModulesRoom::class,
    AppModulesNetwork::class,
    AppModulesContext::class,
    AppModulesViewModel::class
])
@Singleton
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(detailingActivity: DetailingUserActivity)
}