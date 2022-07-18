package com.example.usergit

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.usergit.data.APIRepoUsersListImpl
import com.example.usergit.data.APIRepoUsersDetailingImpl
import com.example.usergit.data.room.CashRepoUsersListImpl
import com.example.usergit.data.room.HistoryDataBase

const val DB_NAME = "History.db"

class App : Application() {
    val repoUsersList by lazy { APIRepoUsersListImpl() }
    val repoUsersDetailing by lazy { APIRepoUsersDetailingImpl(historyDataBase().historyDetailingUserDao()) }
    val cashUserList by lazy { CashRepoUsersListImpl(historyDataBase().historyListUsersDao()) }

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