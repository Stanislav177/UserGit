package com.example.usergit.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserEntity(
    val id: Long,
    val nickname: String,
    val urlAvatar: String,
    val urlUser: String,
    val detailing: UserDetailing,
) : Parcelable

@Parcelize
data class UserDetailing(val name: String, val location: String, val publicRepos: Long) : Parcelable
