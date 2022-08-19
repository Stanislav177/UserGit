package com.example.usergit.data

import com.example.usergit.data.retrofit.RequestAPI
import com.example.usergit.domain.UserDetailingEntity
import com.example.usergit.domain.repos.userDetailing.RepoUserDetailing
import com.example.usergit.ui.detailingUser.appState.AppStateDetailingUser
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy

class APIRepoUsersDetailingImpl(
    private val requestAPI: RequestAPI,
) : RepoUserDetailing {

    private lateinit var loginUser: String

    override fun getDetailingUser(
        onSuccess: (AppStateDetailingUser) -> Unit,
        onError: ((Throwable) -> Unit)?
    ) {
        requestAPI.getDetailingUsersGit(loginUser).subscribeBy(
                onSuccess = {
                    onSuccess(AppStateDetailingUser.Success(converterToUserDetailing(it)))
                },
                onError = {
                    onError!!.invoke(Throwable(it))
                }
            )
    }

    override fun getLoginUser(login: String) {
        loginUser = login
    }

    private fun converterToUserDetailing(dto: DTODetailingUserGit): UserDetailingEntity {
        return UserDetailingEntity(dto.login,
            dto.id,
            dto.avatarURL,
            dto.publicRepos,
            dto.url,
            dto.followers,
            dto.location,
            dto.name)
    }
}