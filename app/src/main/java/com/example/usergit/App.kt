package com.example.usergit

import android.app.Application
import android.content.Context
import com.example.usergit.data.APIRepoUsersDetailingImpl
import com.example.usergit.data.APIRepoUsersListImpl
import com.example.usergit.domain.repos.RepoUserDetailing
import com.example.usergit.domain.repos.RepoUsersList

class App : Application() {
    val repoUsersList: RepoUsersList by lazy { APIRepoUsersListImpl() }
    val repoUsersDetailing: RepoUserDetailing by lazy { APIRepoUsersDetailingImpl() }
}

val Context.app: App get() = applicationContext as App