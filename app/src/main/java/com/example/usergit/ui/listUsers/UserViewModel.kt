package com.example.usergit.ui.listUsers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.usergit.domain.UserEntity
import com.example.usergit.domain.repos.RepoUsersList

class UserViewModel(private val repo: RepoUsersList) : UserContract.ViewModel {

    private var inProgress = false

    private var flagOnRefresh = true

    override fun onRefresh() {
        if (flagOnRefresh)
            loadingUser(repo)
        flagOnRefresh = false

    }

    private fun loadingUser(repo: RepoUsersList) {
        showProgress(true)

        repo.getUsers(
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
        usersLiveData.mutable().postValue(listUser)
    }

    private fun showError(trow: Throwable) {
        errorLiveData.mutable().postValue(trow)
    }

    private fun showProgress(progress: Boolean) {
        progressLiveData.mutable().postValue(progress)
    }

    override val errorLiveData: LiveData<Throwable> = MutableLiveData()

    override val usersLiveData: LiveData<List<UserEntity>> = MutableLiveData()

    override val progressLiveData: LiveData<Boolean> = MutableLiveData()

    private fun <T> LiveData<T>.mutable(): MutableLiveData<T> {
        return this as? MutableLiveData<T> ?: throw IllegalStateException("ОШИБКА!")
    }
}