package com.example.usergit.di.dagger

import com.example.usergit.domain.repos.userDetailing.RepoUserDetailing
import com.example.usergit.domain.repos.userDetailing.RepoUserDetailingCash
import com.example.usergit.domain.repos.usersList.RepoUsersList
import com.example.usergit.domain.repos.usersList.RepoUsersListCash
import com.example.usergit.ui.detailingUser.DetailingViewModel
import com.example.usergit.ui.listUsers.UsersViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModulesViewModel {

    @Provides
    @Singleton
    fun provideViewModelUsersList(
        usersRepo: RepoUsersList,
        usersRepoCash: RepoUsersListCash,
    ): UsersViewModel {
        return UsersViewModel(usersRepo, usersRepoCash)
    }

    @Provides
    @Singleton
    fun provideViewModelDetailingUser(
        repoUserDetailing: RepoUserDetailing,
        repoUserDetailingCash: RepoUserDetailingCash,
    ): DetailingViewModel {
        return DetailingViewModel(repoUserDetailing = repoUserDetailing,
            repoUserDetailingCash = repoUserDetailingCash)
    }
}