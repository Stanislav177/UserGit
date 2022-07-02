package com.example.usergit.data.retrofit

import com.example.usergit.data.DTODetailingUserGit
import com.example.usergit.data.DTOListUsersGit
import com.example.usergit.const.URL_API_END_POINT_USERS
import com.example.usergit.const.URL_API_END_POINT_USERS_DETAILING
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RequestAPI {
    @GET(URL_API_END_POINT_USERS)
    fun getUsersGit(): Single<List<DTOListUsersGit>>


    @GET(URL_API_END_POINT_USERS_DETAILING)
    fun getDetailingUsersGit(
        @Path("username") username: String,
    ): Call<DTODetailingUserGit>

}