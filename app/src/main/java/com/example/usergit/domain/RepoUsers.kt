package com.example.usergit.domain

import com.example.usergit.domain.UserEntity

interface RepoUsers {

    fun getUsers(
        onSuccess: (List<UserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)? = null,
    )
}