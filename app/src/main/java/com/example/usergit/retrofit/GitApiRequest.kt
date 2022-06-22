package com.example.usergit.retrofit

import com.example.usergit.DTOListUsersGit
import retrofit2.Callback

interface GitApiRequest {

    fun getUsersGitList(
        callback: Callback<List<DTOListUsersGit>>,
    )
}