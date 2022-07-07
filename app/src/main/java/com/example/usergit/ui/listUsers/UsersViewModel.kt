package com.example.usergit.ui.listUsers

import com.example.usergit.data.CashRepoUsersListImpl
import com.example.usergit.domain.UserEntity
import com.example.usergit.domain.repos.usersList.RepoUsersList
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject

class UsersViewModel(
    private val repo: RepoUsersList,
    private val repoCash: CashRepoUsersListImpl,
) : UserContract.ViewModel {

    private var flagOnRefresh = true

    override fun onRefresh() {
        if (flagOnRefresh)
            loadingUser()
        flagOnRefresh = false
    }

    private fun loadingUser() {
        showProgress(true)
        repo.getUsersList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    repoCash.deleteCash()
                    showProgress(false)
                    showListUser(it)
                    saveCash(it)

                },
                onError = {
                    showProgress(false)
                    showError(it)
                    showListUser(repoCash.getAllCash())
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

    private fun saveCash(listUser: List<UserEntity>) {
        repoCash.saveListCash(listUser)
    }

    override val errorLiveData: Observable<Throwable> = BehaviorSubject.create()

    override val usersLiveData: Observable<List<UserEntity>> = BehaviorSubject.create()

    override val progressLiveData: Observable<Boolean> = BehaviorSubject.create()

    private fun <T> Observable<T>.mutable(): Subject<T> {
        return this as? Subject<T> ?: throw IllegalStateException("ОШИБКА!")
    }
}