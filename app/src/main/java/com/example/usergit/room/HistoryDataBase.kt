package com.example.usergit.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.usergit.room.detailingUser.HistoryDetailingUser
import com.example.usergit.room.detailingUser.HistoryDetailingUserDao
import com.example.usergit.room.listUsers.HistoryUsersDao
import com.example.usergit.room.listUsers.HistoryUsersList

@Database(entities = [HistoryUsersList::class, HistoryDetailingUser::class],
    version = 1,
    exportSchema = false)
abstract class HistoryDataBase : RoomDatabase() {

    abstract fun historyUsersDao(): HistoryUsersDao
    abstract fun historyDetailingUserDao(): HistoryDetailingUserDao
}