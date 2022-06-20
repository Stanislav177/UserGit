package com.example.usergit

import android.app.Application
import android.content.Context
import com.example.usergit.data.APIRepoUsersImpl
import com.example.usergit.data.LocalRepoUsersImpl
import com.example.usergit.domain.repos.RepoUsers

class App : Application() {

    val userRepoUsers: RepoUsers by lazy { LocalRepoUsersImpl() }

}

val Context.app: App get() = applicationContext as App