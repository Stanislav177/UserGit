package com.example.usergit.domain.repos

import com.example.usergit.DTODetailingUserGit

interface RepoUsersDetailing {

    fun getUsers(
        onSuccess: (DTODetailingUserGit) -> Unit,
        onError: ((Throwable) -> Unit)? = null,
    )

    fun setLoginUser(login: String)
}