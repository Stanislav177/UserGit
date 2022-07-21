package com.example.usergit.data.room

import com.example.usergit.data.room.detailingUser.HistoryDetailingUser
import com.example.usergit.data.room.detailingUser.HistoryDetailingUserDao
import com.example.usergit.domain.UserDetailingEntity
import com.example.usergit.domain.repos.userDetailing.RepoUserDetailingCash
import com.example.usergit.ui.detailingUser.appState.AppStateDetailingUser
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy

class CashRepoDetailingUserImpl(private val cashDetailingUser: HistoryDetailingUserDao) :
    RepoUserDetailingCash {

    private lateinit var loginUser: String

    override fun getDetailingUserCash(): Single<UserDetailingEntity> {
        if (cashDetailingUser.userDetailing(loginUser).isEmpty()){
            return Single.error(Throwable("Нет данных"))
        }

            return converterCashToUserDetailing(cashDetailingUser.userDetailing(loginUser))
    }

    override fun saveDetailingCash(userDetailingEntity: UserDetailingEntity) {
        cashDetailingUser.insert(converterUserDetailingToCashUserDetailing(userDetailingEntity))
    }

    override fun deleteCash() {
        cashDetailingUser.delete()
    }

    override fun getDetailingUser(
        onSuccess: (AppStateDetailingUser) -> Unit,
        onError: ((Throwable) -> Unit)?,
    ) {
        getDetailingUserCash().subscribeBy(
            onSuccess = {
                onSuccess(AppStateDetailingUser.Success(it))
            },
            onError = {
                onError!!.invoke(it)
            }
        )
    }

    override fun getLoginUser(login: String) {
        loginUser = login
    }

    private fun converterCashToUserDetailing(cashDetailing: List<HistoryDetailingUser>): Single<UserDetailingEntity> {
        return Single.just(UserDetailingEntity(cashDetailing[0].login,
            cashDetailing[0].id,
            cashDetailing[0].avatarURL,
            cashDetailing[0].publicRepos,
            cashDetailing[0].url,
            cashDetailing[0].followers,
            cashDetailing[0].location,
            cashDetailing[0].name))
    }

    private fun converterUserDetailingToCashUserDetailing(userDetailingEntity: UserDetailingEntity) =
        HistoryDetailingUser(
            userDetailingEntity.login,
            userDetailingEntity.id,
            userDetailingEntity.avatarURL,
            userDetailingEntity.publicRepos,
            userDetailingEntity.url,
            userDetailingEntity.followers,
            userDetailingEntity.location,
            userDetailingEntity.name)
}