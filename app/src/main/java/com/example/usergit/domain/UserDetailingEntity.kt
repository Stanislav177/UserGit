package com.example.usergit.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserDetailingEntity(
    val login: String,
    val id: Long,
    val avatarURL: String,
    val publicRepos: Long,
    val url: String,
    val followers: Long,
    val location: String? = "",
    val name: String,
) : Parcelable
