package com.example.usergit.ui.detailingUser

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.usergit.data.APIRepoUsersDetailingImpl
import com.example.usergit.domain.repos.RepoUserDetailing
import com.example.usergit.ui.detailingUser.appState.AppStateDetailingUser
import com.example.usergit.ui.detailingUser.appState.AppStateDetailingUserError
import com.example.usergit.utils.SingleEventLiveDataError

class DetailingViewModel(
    private val liveData: MutableLiveData<AppStateDetailingUser> = MutableLiveData(),
    private val repo: RepoUserDetailing = APIRepoUsersDetailingImpl(),
) : ViewModel(), AppStateDetailingUserError {

    fun getLiveData(): LiveData<AppStateDetailingUser> {
        return liveData
    }

    fun startRequest(login: String) {
        loadingUser(login, repo)
    }

    private fun loadingUser(l: String, repoLoading: RepoUserDetailing) {
        repoLoading.getLoginUser(l)
        repoLoading.getDetailingUser(
            onSuccess = {
                showProgress(AppStateDetailingUser.LoadingProgress(false))
                Handler(Looper.getMainLooper()).post {
                    showListUser(it)
                }
            }, onError = {
                showError(it)
            }
        )
    }

    private fun showError(it: Throwable) {
        error.mutable().postValue(it)
    }

    private fun showListUser(it: AppStateDetailingUser) {
        liveData.postValue(it)
    }

    private fun showProgress(it: AppStateDetailingUser) {
        liveData.postValue(it)
    }

    private fun <T> LiveData<T>.mutable(): MutableLiveData<T> {
        return this as? MutableLiveData<T> ?: throw IllegalStateException("ОШИБКА!")
    }

    override val error: LiveData<Throwable> = SingleEventLiveDataError()

}