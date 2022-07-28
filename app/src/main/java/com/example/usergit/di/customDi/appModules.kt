package com.example.usergit.di.customDi

import androidx.room.Room
import com.example.dil.Modules
import com.example.dil.getInline
import com.example.usergit.data.APIRepoUsersDetailingImpl
import com.example.usergit.data.APIRepoUsersListImpl
import com.example.usergit.data.retrofit.RequestAPI
import com.example.usergit.data.room.CashRepoDetailingUserImpl
import com.example.usergit.data.room.CashRepoUsersListImpl
import com.example.usergit.data.room.HistoryDataBase
import com.example.usergit.domain.repos.userDetailing.RepoUserDetailing
import com.example.usergit.domain.repos.userDetailing.RepoUserDetailingCash
import com.example.usergit.domain.repos.usersList.RepoUsersList
import com.example.usergit.domain.repos.usersList.RepoUsersListCash
import com.example.usergit.ui.detailingUser.DetailingViewModel
import com.example.usergit.ui.listUsers.UsersViewModel
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appModules = Modules {

    val url = "https://api.github.com"
    val nameDB = "History.db"

    singleton<Retrofit> {
        Retrofit.Builder().baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create()).build()
    }
    singleton<RequestAPI> { getInline<Retrofit>().create(RequestAPI::class.java) }

    singleton {
        Room.databaseBuilder(contextApp.applicationContext, HistoryDataBase::class.java, nameDB)
            .allowMainThreadQueries()
            .build()
    }
    singleton { getInline<HistoryDataBase>().historyListUsersDao() }
    singleton { getInline<HistoryDataBase>().historyDetailingUserDao() }

    singleton<RepoUsersList>("API") { APIRepoUsersListImpl(getInline()) }
    singleton<RepoUsersListCash>("CASH") { CashRepoUsersListImpl(getInline()) }

    singleton<RepoUserDetailing> { APIRepoUsersDetailingImpl(getInline()) }
    singleton<RepoUserDetailingCash> { CashRepoDetailingUserImpl(getInline()) }

    viewModel { UsersViewModel() }
    viewModel {
        DetailingViewModel(
            repoUserDetailing = getInline(),
            repoUserDetailingCash = getInline())
    }
}