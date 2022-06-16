package com.example.usergit.retrofit

import com.example.usergit.DTOUsersGit
import com.example.usergit.const.URL_API_END_POINT_USERS
import retrofit2.Call
import retrofit2.http.GET

interface RequestAPI {
    @GET(URL_API_END_POINT_USERS)
    fun getUsersGit(): Call<DTOUsersGit>
}