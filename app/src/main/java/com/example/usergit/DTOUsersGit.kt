package com.example.usergit

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class DTOUsersGit(
    val users: ArrayList<List<ListUsers>>,
) : Parcelable

@Parcelize
data class ListUsers(
    val login: String,
    val id: Long,

    @SerializedName("avatar_url")
    val avatarURL: String,
) : Parcelable
