package com.example.usergit.data.room.listUsers

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HistoryUsersDao {

    @Query("DELETE FROM history_users_list")
    fun delete()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: List<HistoryUsersList>)

    @Query("SELECT*FROM history_users_list")
    fun all(): List<HistoryUsersList>
}