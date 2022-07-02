package com.example.usergit.ui.listUsers

import com.example.usergit.domain.UserEntity
import com.example.usergit.domain.repos.RepoUsersList
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject

class UsersViewModel(private val repo: RepoUsersList) : UserContract.ViewModel {

    private var flagOnRefresh = true

    override fun onRefresh() {
        if (flagOnRefresh)
            loadingUser(repo)
        flagOnRefresh = false
    }

    private fun loadingUser(repoLoading: RepoUsersList) {
        showProgress(true)
        repoLoading.getUsersList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    showProgress(false)
                    showListUser(it)
                },
                onError = {
                    showProgress(false)
                    showError(it)
                }
            )
    }

    private fun showListUser(listUser: List<UserEntity>) {
        usersLiveData.mutable().onNext(listUser)
    }

    private fun showError(trow: Throwable) {
        errorLiveData.mutable().onNext(trow)
    }

    private fun showProgress(progress: Boolean) {
        progressLiveData.mutable().onNext(progress)
    }

    override val errorLiveData: Observable<Throwable> = BehaviorSubject.create()

    override val usersLiveData: Observable<List<UserEntity>> = BehaviorSubject.create()

    override val progressLiveData: Observable<Boolean> = BehaviorSubject.create()

    private fun <T> Observable<T>.mutable(): Subject<T> {
        return this as? Subject<T> ?: throw IllegalStateException("ОШИБКА!")
    }
}