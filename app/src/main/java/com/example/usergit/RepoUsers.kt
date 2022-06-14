package com.example.usergit

interface RepoUsers {

    fun getUsers(
        onSuccess: (List<UserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)? = null,
    )
}