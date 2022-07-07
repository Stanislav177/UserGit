package com.example.usergit

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.usergit.const.DB_NAME
import com.example.usergit.data.RepoUsersDetailingImpl
import com.example.usergit.data.APIRepoUsersListImpl
import com.example.usergit.domain.repos.userDetailing.RepoUserDetailing
import com.example.usergit.domain.repos.usersList.RepoUsersList
import com.example.usergit.room.HistoryDataBase

class App : Application() {
    val repoUsersList: RepoUsersList by lazy { APIRepoUsersListImpl() }
    val repoUsersDetailing: RepoUserDetailing by lazy { RepoUsersDetailingImpl(historyDataBase().historyDetailingUserDao()) }

    private var db: HistoryDataBase? = null

    fun historyDataBase(): HistoryDataBase {

        if (db == null) {
            db = Room.databaseBuilder(app, HistoryDataBase::class.java, DB_NAME)
                .allowMainThreadQueries()
                .build()
        }
        return db!!
    }
}

val Context.app: App get() = applicationContext as App