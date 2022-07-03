package com.example.usergit.ui.detailingUser.appState

import androidx.lifecycle.LiveData
import com.example.usergit.data.DTODetailingUserGit
import io.reactivex.rxjava3.core.Observable

sealed class AppStateDetailingUser {
    data class LoadingProgress(val progress: Boolean) : AppStateDetailingUser()
    data class Success(val dtoDetailingUserGit: DTODetailingUserGit) : AppStateDetailingUser()
}

interface AppStateDetailingUserError {
    val error:Observable<Throwable>
}

