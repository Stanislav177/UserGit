package com.example.usergit.data

import com.example.usergit.data.retrofit.RetrofitAPI
import com.example.usergit.domain.repos.RepoUserDetailing
import com.example.usergit.ui.detailingUser.appState.AppStateDetailingUser
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy

class APIRepoUsersDetailingImpl : RepoUserDetailing {

    private val requestAPI by lazy { RetrofitAPI.getStartRetrofitAPI() }

    private lateinit var loginUser: String

    override fun getDetailingUser(
        onSuccess: (AppStateDetailingUser) -> Unit,
        onError: ((Throwable) -> Unit)?,
    ) {
        requestAPI.getDetailingUsersGit(loginUser).observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    onSuccess(AppStateDetailingUser.Success(it))
                },
                onError = {
                    onError!!.invoke(Throwable(it))
                }
            )
    }

    override fun getLoginUser(login: String) {
        loginUser = login
    }
}