package com.example.usergit.domain.repos

import com.example.usergit.domain.UserEntity


interface RepoUsersList {

    fun getUsersList(
        onSuccess: (List<UserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )
}