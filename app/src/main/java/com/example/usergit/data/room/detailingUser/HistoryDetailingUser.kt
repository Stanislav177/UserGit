package com.example.usergit.data.room.detailingUser

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_detailing_user")
data class HistoryDetailingUser(
    val login: String,
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val avatarURL: String,
    val publicRepos: Long,
    val url: String,
    val followers: Long,
    val location: String? = "",
    val name: String,
)
