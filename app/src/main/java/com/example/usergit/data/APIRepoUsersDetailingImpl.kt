package com.example.usergit.data

import com.example.usergit.data.retrofit.RetrofitAPI
import com.example.usergit.domain.UserDetailingEntity
import com.example.usergit.domain.repos.userDetailing.RepoUserDetailing
import com.example.usergit.domain.repos.userDetailing.RepoUserDetailingCash
import com.example.usergit.data.room.detailingUser.HistoryDetailingUser
import com.example.usergit.data.room.detailingUser.HistoryDetailingUserDao
import com.example.usergit.ui.detailingUser.appState.AppStateDetailingUser
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy

class APIRepoUsersDetailingImpl(private val cashDetailingUser: HistoryDetailingUserDao) :
    RepoUserDetailing,
    RepoUserDetailingCash {

    private val requestAPI by lazy { RetrofitAPI.getStartRetrofitAPI() }

    private lateinit var loginUser: String

    override fun getDetailingUser(
        onSuccess: (AppStateDetailingUser) -> Unit,
        onError: ((Throwable) -> Unit)?,
    ) {
        requestAPI.getDetailingUsersGit(loginUser).observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    saveDetailingCash(it)
                    onSuccess(AppStateDetailingUser.Success(converterToUserDetailing(it)))
                },
                onError = {
                    onSuccess(AppStateDetailingUser.Success(getDetailingUserCash()))
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

    override fun getDetailingUserCash(): UserDetailingEntity {
        return converterCashToUserDetailing(cashDetailingUser.userDetailing(loginUser))
    }

    override fun saveDetailingCash(dtoUser: DTODetailingUserGit) {
        cashDetailingUser.insert(converterUserDetailingToCashUserDetailing(dtoUser))
    }

    override fun deleteCash() {
        cashDetailingUser.delete()
    }

    private fun converterCashToUserDetailing(cashDetailing: List<HistoryDetailingUser>): UserDetailingEntity {
        return UserDetailingEntity(cashDetailing[0].login,
            cashDetailing[0].id,
            cashDetailing[0].avatarURL,
            cashDetailing[0].publicRepos,
            cashDetailing[0].url,
            cashDetailing[0].followers,
            cashDetailing[0].location,
            cashDetailing[0].name)
    }

    private fun converterUserDetailingToCashUserDetailing(dto: DTODetailingUserGit) =
        HistoryDetailingUser(
            dto.login,
            dto.id,
            dto.avatarURL,
            dto.publicRepos,
            dto.url,
            dto.followers,
            dto.location,
            dto.name)
}