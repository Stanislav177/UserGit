package com.example.usergit

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.usergit.data.APIRepoUsersDetailingImpl
import com.example.usergit.data.APIRepoUsersListImpl
import com.example.usergit.data.retrofit.RequestAPI
import com.example.usergit.data.room.CashRepoDetailingUserImpl
import com.example.usergit.data.room.CashRepoUsersListImpl
import com.example.usergit.data.room.HistoryDataBase
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val DB_NAME = "History.db"
const val URL_API_BASE = "https://api.github.com"

class App : Application() {
    val repoUsersList by lazy { APIRepoUsersListImpl(startRetrofit()) }
    val repoUsersDetailing by lazy {
        APIRepoUsersDetailingImpl(startRetrofit())
    }
    val cashUsersList by lazy { CashRepoUsersListImpl(historyDataBase().historyListUsersDao()) }
    val cashDetailingUser by lazy { CashRepoDetailingUserImpl(historyDataBase().historyDetailingUserDao()) }

    private fun startRetrofit(): RequestAPI {
        val retrofit = Retrofit.Builder().baseUrl(URL_API_BASE)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create()).build()
        return retrofit.create(RequestAPI::class.java)
    }

    private var db: HistoryDataBase? = null

    private fun historyDataBase(): HistoryDataBase {
        if (db == null) {
            db = Room.databaseBuilder(app, HistoryDataBase::class.java, DB_NAME)
                .allowMainThreadQueries()
                .build()
        }
        return db!!
    }
}

val Context.app: App get() = applicationContext as App