package com.example.usergit.room.listUsers

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_users_list")
data class HistoryUsersList(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val login: String,
    val urlAvatar: String,
    val urlUser: String,
)
