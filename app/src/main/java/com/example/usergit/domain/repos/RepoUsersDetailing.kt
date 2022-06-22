package com.example.usergit.domain.repos

import com.example.usergit.DTODetailingUserGit
import com.example.usergit.domain.UserEntityDetailing

interface RepoUsersDetailing {

    fun getUsers(
        onSuccess: (DTODetailingUserGit) -> Unit,
        onError: ((Throwable) -> Unit)? = null,
    )

    fun setLoginUser(login: String)
}