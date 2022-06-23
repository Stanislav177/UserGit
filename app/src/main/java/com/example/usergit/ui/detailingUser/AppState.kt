package com.example.usergit.ui.detailingUser

import com.example.usergit.DTODetailingUserGit

sealed class AppState {
    data class LoadingProgress(val progress: Boolean) : AppState()
    data class Success(val dtoDetailingUserGit: DTODetailingUserGit) : AppState()
    data class Error(val error: Throwable) : AppState()
}
