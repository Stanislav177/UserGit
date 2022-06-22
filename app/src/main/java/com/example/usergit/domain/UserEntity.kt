package com.example.usergit.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserEntity(
    val id: Long,
    val login: String,
    val urlAvatar: String,
    val urlUser: String,
) : Parcelable