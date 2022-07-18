package com.example.usergit.data.room.detailingUser

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HistoryDetailingUserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entityDetailing: HistoryDetailingUser)

    @Query("DELETE FROM history_detailing_user")
    fun delete()

    @Query("SELECT*FROM history_detailing_user WHERE login =:userLogin")
    fun userDetailing(userLogin: String): List<HistoryDetailingUser>
}