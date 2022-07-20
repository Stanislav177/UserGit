package com.example.usergit.ui.detailingUser

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModel
import com.example.usergit.domain.repos.userDetailing.RepoUserDetailing
import com.example.usergit.domain.repos.userDetailing.RepoUserDetailingCash
import com.example.usergit.ui.detailingUser.appState.AppStateDetailingUser
import com.example.usergit.ui.detailingUser.appState.AppStateDetailingUserError
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject

class DetailingViewModel(
    private val liveData: Observable<AppStateDetailingUser> = BehaviorSubject.create(),
    private val repoUserDetailing: RepoUserDetailing,
    private val repoUserDetailingCash: RepoUserDetailingCash,
) : ViewModel(), AppStateDetailingUserError {

    private lateinit var loginUser: String

    fun getLiveData(): Observable<AppStateDetailingUser> {
        return liveData
    }

    fun startRequest(login: String) {
        loginUser = login
        loadingUser(login)
    }

    private fun loadingUser(l: String) {
        repoUserDetailing.getLoginUser(l)
        repoUserDetailing.getDetailingUser(
            onSuccess = {
                showProgress(AppStateDetailingUser.LoadingProgress(false))
                saveCash(it)
                Handler(Looper.getMainLooper()).post {
                    showListUser(it)
                }
            }, onError = {
                showProgress(AppStateDetailingUser.LoadingProgress(false))
                loadingCashDetailingUser()
                showError(it)
            }
        )
    }

    private fun loadingCashDetailingUser() {
        with(repoUserDetailingCash) {
            getLoginUser(loginUser)
            getDetailingUser(
                onSuccess = {
                    Handler(Looper.getMainLooper()).post {
                        showListUser(it)
                    }
                }, onError = {
                    showError(it)
                }
            )
        }

    }

    private fun showError(it: Throwable) {
        error.observeOn(AndroidSchedulers.mainThread())
        error.mutable().onNext(it)
    }

    private fun showListUser(it: AppStateDetailingUser) {
        liveData.mutable().onNext(it)
    }

    private fun showProgress(appState: AppStateDetailingUser) {
        liveData.mutable().onNext(appState)
    }

    private fun saveCash(appState: AppStateDetailingUser) {
        repoUserDetailingCash.deleteCash()
        when (appState) {
            is AppStateDetailingUser.Success -> {
                repoUserDetailingCash.saveDetailingCash(appState.detailingUserGit)
            }
        }
    }

    private fun <T> Observable<T>.mutable(): Subject<T> {
        return this as? Subject<T> ?: throw IllegalStateException("ОШИБКА!")
    }

    override val error: Observable<Throwable> = BehaviorSubject.create()

}