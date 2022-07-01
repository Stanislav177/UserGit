package com.example.usergit.ui.listUsers

import androidx.lifecycle.LiveData
import com.example.usergit.domain.UserEntity

interface UserContract {

    interface ViewModel {
        val errorLiveData: LiveData<Throwable>
        val usersLiveData: LiveData<List<UserEntity>>
        val progressLiveData: LiveData<Boolean>
        fun onRefresh()

    }
}