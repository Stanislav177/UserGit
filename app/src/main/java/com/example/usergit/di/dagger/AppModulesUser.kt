package com.example.usergit.di.dagger

import com.example.usergit.data.APIRepoUsersDetailingImpl
import com.example.usergit.data.APIRepoUsersListImpl
import com.example.usergit.data.retrofit.RequestAPI
import com.example.usergit.data.room.CashRepoDetailingUserImpl
import com.example.usergit.data.room.CashRepoUsersListImpl
import com.example.usergit.data.room.detailingUser.HistoryDetailingUserDao
import com.example.usergit.data.room.listUsers.HistoryUsersDao
import com.example.usergit.domain.repos.userDetailing.RepoUserDetailing
import com.example.usergit.domain.repos.userDetailing.RepoUserDetailingCash
import com.example.usergit.domain.repos.usersList.RepoUsersList
import com.example.usergit.domain.repos.usersList.RepoUsersListCash
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModulesUser {

    @Provides
    @Singleton
    fun provideRepoUsersList(request: RequestAPI): RepoUsersList {
        return APIRepoUsersListImpl(request)
    }

    @Provides
    @Singleton
    fun provideRepoUsersListCash(cashHistoryUsersDao: HistoryUsersDao): RepoUsersListCash {
        return CashRepoUsersListImpl(cashHistoryUsersDao)
    }

    @Provides
    @Singleton
    fun provideRepoUserDetailing(request: RequestAPI): RepoUserDetailing {
        return APIRepoUsersDetailingImpl(request)
    }

    @Provides
    @Singleton
    fun provideRepoUserDetailingCash(cashHistoryDetailingUsersDao: HistoryDetailingUserDao): RepoUserDetailingCash {
        return CashRepoDetailingUserImpl(cashHistoryDetailingUsersDao)
    }
}