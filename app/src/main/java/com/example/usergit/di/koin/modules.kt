package com.example.usergit.di

import androidx.room.Room
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
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appNetworkModule = module {

    single<RepoUsersList> {
        APIRepoUsersListImpl(get())
    }

    single<RepoUserDetailing> {
        APIRepoUsersDetailingImpl(get())
    }

    single(named("url")) {
        "https://api.github.com"
    }

    single<Retrofit> {
        Retrofit.Builder().baseUrl(get<String>(named("url")))
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create()).build()
    }
    single<RequestAPI> { get<Retrofit>().create(RequestAPI::class.java) }
}

val appRoomModule = module {
    single { "History.db" }

    single<RepoUsersListCash> {
        CashRepoUsersListImpl(get())
    }

    single<RepoUserDetailingCash> {
        CashRepoDetailingUserImpl(get())
    }

    single<HistoryDataBase?> {
        Room.databaseBuilder(androidContext(), HistoryDataBase::class.java, get())
            .allowMainThreadQueries()
            .build()
    }
    single {
        get<HistoryDataBase>().historyListUsersDao()
    }
    single {
        get<HistoryDataBase>().historyDetailingUserDao()
    }
}

val appViewModels = module {
    viewModel {
        UsersViewModel()
    }
    viewModel {
        DetailingViewModel(
            repoUserDetailingCash = get(), repoUserDetailing = get()
        )
    }
}