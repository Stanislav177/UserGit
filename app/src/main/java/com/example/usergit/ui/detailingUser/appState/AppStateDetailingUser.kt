package com.example.usergit.ui.detailingUser.appState

import com.example.usergit.domain.UserDetailingEntity
import io.reactivex.rxjava3.core.Observable

sealed class AppStateDetailingUser {
    data class LoadingProgress(val progress: Boolean) : AppStateDetailingUser()
    data class Success(val detailingUserGit: UserDetailingEntity) : AppStateDetailingUser()
}

interface AppStateDetailingUserError {
    val error: Observable<Throwable>
}

