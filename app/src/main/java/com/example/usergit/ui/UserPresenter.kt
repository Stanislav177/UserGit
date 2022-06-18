package com.example.usergit.ui

import com.example.usergit.domain.repos.RepoUsers

class UserPresenter(private val repo: RepoUsers) : UserContract.Presenter {

    private var viewPresenter: UserContract.View? = null

    override fun attach(view: UserContract.View) {
        this.viewPresenter = view
    }

    override fun detach() {
        viewPresenter = null
    }

    override fun onRefresh() {
        loadingUser(repo)
    }

    private fun loadingUser(repo: RepoUsers) {
        viewPresenter?.showProgress(true)
        repo.getUsers(
            onSuccess = {
                viewPresenter?.showUsers(it)
                viewPresenter?.showProgress(false)
            },
            onError = {
                viewPresenter?.showProgress(false)
                viewPresenter?.showError(it)

            }
        )
    }


//    private fun loadingUser(repoUsers: RepoUsers) {
//        viewPresenter.showProgress(true)
//        viewPresenter.repoUsers.getUsers(
//            onSuccess = {
//                viewPresenter.showUsers(it)
//                viewPresenter.showProgress(false)
//            },
//            onError = {
//                viewPresenter.showProgress(false)
//                viewPresenter.showError(it)
//
//            }
//        )
//    }
}