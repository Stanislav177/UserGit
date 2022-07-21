package com.example.usergit.data

import android.os.Parcelable
import com.example.usergit.domain.UserEntity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DTOListUsersGit(
    val login: String,
    val id: Long,

    @SerializedName("avatar_url")
    val avatarURL: String,
    val url: String,
) : Parcelable {
    fun toUserEntity() = UserEntity(id, login, avatarURL, url)
}


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
    val location: String = "null",
    val name: String
) : Parcelable
