package com.example.usergit.domain.repos

import com.example.usergit.ui.detailingUser.appState.AppStateDetailingUser
import com.example.usergit.ui.detailingUser.appState.AppStateDetailingUserError

interface RepoUserDetailing {
    fun getDetailingUser(
        onSuccess: (AppStateDetailingUser) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )

    fun getLoginUser(
        login: String,
    )
}