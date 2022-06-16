package com.example.usergit.retrofit

import com.example.usergit.DTOUsersGit
import retrofit2.Callback

interface GitApiRequest {

    fun getUsersGitList(
        callback: Callback<DTOUsersGit>
    )
}