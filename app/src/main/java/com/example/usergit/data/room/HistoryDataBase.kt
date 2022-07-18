package com.example.usergit.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.usergit.data.room.detailingUser.HistoryDetailingUser
import com.example.usergit.data.room.detailingUser.HistoryDetailingUserDao
import com.example.usergit.data.room.listUsers.HistoryUsersDao
import com.example.usergit.data.room.listUsers.HistoryUsersList

@Database(entities = [HistoryUsersList::class, HistoryDetailingUser::class],
    version = 1,
    exportSchema = false)
abstract class HistoryDataBase : RoomDatabase() {

    abstract fun historyListUsersDao(): HistoryUsersDao
    abstract fun historyDetailingUserDao(): HistoryDetailingUserDao
}