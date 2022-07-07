package com.example.usergit.domain.repos.usersList

import com.example.usergit.domain.UserEntity
import io.reactivex.rxjava3.core.Single


interface RepoUsersList {

    fun getUsersList(
        onSuccess: (List<UserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)? = null,
    )

    fun getUsersList(): Single<List<UserEntity>>
}