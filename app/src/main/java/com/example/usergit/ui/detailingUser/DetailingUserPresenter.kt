package com.example.usergit.ui.detailingUser

import com.example.usergit.DTODetailingUserGit
import com.example.usergit.domain.UserEntityDetailing
import com.example.usergit.domain.repos.RepoUsersDetailing

class DetailingUserPresenter(private val repoUsersDetailing: RepoUsersDetailing) :
    DetailingUserContract.Presenter {

    private var view: DetailingUserContract.View? = null
    private var dataUserPresenter: DTODetailingUserGit? = null
    private var loginUser: String? = null
    private var inProgress = false


    override fun attach(v: DetailingUserContract.View) {
        this.view = v
        loadingUser(repoUsersDetailing, loginUser!!)
    }

    override fun detach() {
        view = null
    }

    override fun loginUser(l: String) {
        loginUser = l
    }

    private fun loadingUser(repoUsersDetailing: RepoUsersDetailing, login: String) {
        repoUsersDetailing.setLoginUser(login)
        repoUsersDetailing.getUsers(
            onSuccess = {
                dataUserPresenter = it
                showUser(it)
            },
            onError = {
                showError(it)
            }
        )

    }

    private fun showError(t: Throwable) {
        // Error
    }

    private fun showUser(user: DTODetailingUserGit) {
        view?.let { it.showUser(user) }
    }

    fun showProgress(b: Boolean) {
        TODO("Not yet implemented")
    }

}