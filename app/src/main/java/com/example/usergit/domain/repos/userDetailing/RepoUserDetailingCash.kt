package com.example.usergit.domain.repos.userDetailing

import com.example.usergit.domain.UserDetailingEntity
import com.example.usergit.ui.detailingUser.appState.AppStateDetailingUser
import io.reactivex.rxjava3.core.Single

interface RepoUserDetailingCash {
    fun getDetailingUserCash(): Single<UserDetailingEntity>
    fun saveDetailingCash(userDetailingEntity: UserDetailingEntity)
    fun deleteCash()

    fun getDetailingUser(
        onSuccess: (AppStateDetailingUser) -> Unit,
        onError: ((Throwable) -> Unit)? = null,
    )

    fun getLoginUser(
        login: String,
    )
}