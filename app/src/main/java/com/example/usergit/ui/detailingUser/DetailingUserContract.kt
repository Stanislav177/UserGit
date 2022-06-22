package com.example.usergit.ui.detailingUser

import com.example.usergit.DTODetailingUserGit
import com.example.usergit.domain.UserEntityDetailing

interface DetailingUserContract {

    interface Presenter {
        fun attach(v: View)
        fun detach()
        fun loginUser(l: String)
    }

    interface View {
        fun showError(t: Throwable)
        fun showUser(user: DTODetailingUserGit)
        fun showProgress(b: Boolean)
    }
}