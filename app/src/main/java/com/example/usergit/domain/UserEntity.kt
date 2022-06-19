package com.example.usergit.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserEntity(val id: Long, val nickname: String, val urlAvatar: String) : Parcelable
