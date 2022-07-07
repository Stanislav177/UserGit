package com.example.usergit.ui.detailingUser

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModel
import com.example.usergit.domain.repos.userDetailing.RepoUserDetailing
import com.example.usergit.ui.detailingUser.appState.AppStateDetailingUser
import com.example.usergit.ui.detailingUser.appState.AppStateDetailingUserError
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject

class DetailingViewModel(
    private val liveData: Observable<AppStateDetailingUser> = BehaviorSubject.create(),
    private val repo: RepoUserDetailing,

    ) : ViewModel(), AppStateDetailingUserError {

    fun getLiveData(): Observable<AppStateDetailingUser> {
        return liveData
    }

    fun startRequest(login: String) {
        loadingUser(login)
    }

    private fun loadingUser(l: String) {
        repo.getLoginUser(l)
        repo.getDetailingUser(
            onSuccess = {
                showProgress(AppStateDetailingUser.LoadingProgress(false))
                Handler(Looper.getMainLooper()).post {
                    showListUser(it)
                }
            }, onError = {
                showProgress(AppStateDetailingUser.LoadingProgress(false))

                showError(it)
            }
        )
    }

    private fun showError(it: Throwable) {
        error.observeOn(AndroidSchedulers.mainThread())
        error.mutable().onNext(it)
    }

    private fun showListUser(it: AppStateDetailingUser) {
        liveData.mutable().onNext(it)
    }

    private fun showProgress(it: AppStateDetailingUser) {
        liveData.mutable().onNext(it)
    }

    private fun <T> Observable<T>.mutable(): Subject<T> {
        return this as? Subject<T> ?: throw IllegalStateException("ОШИБКА!")
    }

    override val error: Observable<Throwable> = BehaviorSubject.create()

}