package com.example.usergit.ui

import com.example.usergit.domain.UserEntity

interface UserContract {

    interface Presenter {
        fun attach(view: View)
        fun detach()

        fun onRefresh()

    }

    interface View {
        fun showError(throwable: Throwable)
        fun showUsers(data: List<UserEntity>)
        fun showProgress(b: Boolean)
    }
}