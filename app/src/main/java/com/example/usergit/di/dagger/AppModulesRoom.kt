package com.example.usergit.di.dagger

import android.content.Context
import androidx.room.Room
import com.example.usergit.data.room.HistoryDataBase
import com.example.usergit.data.room.detailingUser.HistoryDetailingUserDao
import com.example.usergit.data.room.listUsers.HistoryUsersDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModulesRoom {
    private val nameDB = "History.db"

    @Provides
    @Singleton
    fun provideHistoryDataBase(context: Context): HistoryDataBase {
        return Room.databaseBuilder(context, HistoryDataBase::class.java, nameDB)
            .allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    fun provideHistoryListUsers(historyDataBase: HistoryDataBase): HistoryUsersDao {
        return historyDataBase.historyListUsersDao()
    }

    @Provides
    @Singleton
    fun provideHistoryDetailingUser(historyDataBase: HistoryDataBase): HistoryDetailingUserDao {
        return historyDataBase.historyDetailingUserDao()
    }
}