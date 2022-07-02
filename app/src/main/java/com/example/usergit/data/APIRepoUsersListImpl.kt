package com.example.usergit.data

import com.example.usergit.data.retrofit.RetrofitAPI
import com.example.usergit.domain.UserEntity
import com.example.usergit.domain.repos.RepoUsersList
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy

class APIRepoUsersListImpl : RepoUsersList {

    private val requestAPI by lazy { RetrofitAPI.getStartRetrofitAPI() }

    override fun getUsersList(
        onSuccess: (List<UserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)?,
    ) {
        requestAPI.getUsersGit().subscribeBy(
            onSuccess = { onSuccess(converterDTOtoListUsers(it)) },
            onError = { onError!!.invoke(Throwable(it)) }
        )
    }

    override fun getUsersList(): Single<List<UserEntity>> = requestAPI.getUsersGit().map { users ->
        users.map { it.toUserEntity() }
    }

    private fun converterDTOtoListUsers(it: List<DTOListUsersGit>): List<UserEntity> {
        val list = mutableListOf<UserEntity>()
        val sizeDTO = it.size
        for (i in 0 until sizeDTO) {
            list.add(UserEntity(it[i].id, it[i].login, it[i].avatarURL, it[i].url))
        }
        return list

    }

}