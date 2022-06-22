package com.example.usergit.retrofit

import com.example.usergit.DTODetailingUserGit
import com.example.usergit.DTOListUsersGit
import com.example.usergit.const.URL_API_END_POINT_USERS
import com.example.usergit.const.URL_API_END_POINT_USERS_DETAILING
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RequestAPI {
    @GET(URL_API_END_POINT_USERS)
    fun getUsersGit(): Call<List<DTOListUsersGit>>


    @GET(URL_API_END_POINT_USERS_DETAILING)
    fun getDetailingUsersGit(
        @Path("username") username: String,
    ): Call<DTODetailingUserGit>

}