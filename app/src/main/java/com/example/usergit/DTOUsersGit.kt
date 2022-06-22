package com.example.usergit

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DTOListUsersGit(
    val login: String,
    val id: Long,

    @SerializedName("avatar_url")
    val avatarURL: String,

    val url: String,
) : Parcelable

@Parcelize
data class DTODetailingUserGit(
    val login: String,
    val id: Long,

    @SerializedName("avatar_url")
    val avatarURL: String,

    @SerializedName("public_repos")
    val publicRepos: Long,
    @SerializedName("html_url")
    val url: String,
    val followers: Long,
    val location: String,
    val name: String,
) : Parcelable
