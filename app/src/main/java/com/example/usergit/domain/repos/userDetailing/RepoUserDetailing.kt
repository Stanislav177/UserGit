package com.example.usergit.domain.repos.userDetailing

import com.example.usergit.ui.detailingUser.appState.AppStateDetailingUser

interface RepoUserDetailing {
    fun getDetailingUser(
        onSuccess: (AppStateDetailingUser) -> Unit,
        onError: ((Throwable) -> Unit)? = null,
    )

    fun getLoginUser(
        login: String
    )
}