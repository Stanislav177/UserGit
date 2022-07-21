package com.example.usergit.ui.listUsers

import com.example.usergit.domain.UserEntity
import io.reactivex.rxjava3.core.Observable

interface UserContract {

    interface ViewModel {
        val errorLiveData: Observable<Throwable>
        val usersLiveData: Observable<List<UserEntity>>
        val progressLiveData: Observable<Boolean>
        fun onRefresh()
        fun onLoadingCash()
    }
}