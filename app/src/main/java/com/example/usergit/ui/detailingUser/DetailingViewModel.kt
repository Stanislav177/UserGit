package com.example.usergit.ui.detailingUser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.usergit.data.APIRepoUsersDetailingImpl

class DetailingViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val apiRepo: APIRepoUsersDetailingImpl = APIRepoUsersDetailingImpl(),
) : ViewModel() {

    fun getLiveData(): LiveData<AppState> {
        return liveData
    }

    fun loading(loginUser: String) {
        apiRepo.setLoginUser(loginUser)
        apiRepo.getUsers(
            onSuccess = {
                liveData.postValue(AppState.Success(it))
            },
            onError = {

            }
        )
    }

}