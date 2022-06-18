package com.example.usergit.ui

import com.example.usergit.domain.UserEntity
import com.example.usergit.domain.repos.RepoUsers

class UserPresenter(private val repo: RepoUsers) : UserContract.Presenter {

    private var view: UserContract.View? = null
    private var dataListPresenter: List<UserEntity>? = null
    private var inProgress: Boolean = false

    private var flagOnRefresh: Boolean = true

    override fun attach(view: UserContract.View) {
        this.view = view
        dataListPresenter?.let { view.showUsers(it) }
        view.showProgress(inProgress)
    }

    override fun detach() {
        view = null
    }

    override fun onRefresh() {
        if (flagOnRefresh)
            loadingUser(repo)
        flagOnRefresh = false


    }

    private fun loadingUser(repo: RepoUsers) {
        showProgress(true)
        inProgress = true
        repo.getUsers(
            onSuccess = {
                dataListPresenter = it
                showListUser(it)
                showProgress(false)
                inProgress = false
            },
            onError = {
                showProgress(false)
                showError(it)
                inProgress = false
            }
        )
    }

    private fun showListUser(listUser: List<UserEntity>) {
        view?.showUsers(listUser)
    }

    private fun showError(trow: Throwable) {
        view?.showError(trow)
        flagOnRefresh = true
    }

    private fun showProgress(progress: Boolean) {
        view?.showProgress(progress)
    }
}