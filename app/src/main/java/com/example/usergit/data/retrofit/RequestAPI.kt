package com.example.usergit.data.retrofit

import com.example.usergit.data.DTODetailingUserGit
import com.example.usergit.data.DTOListUsersGit
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

const val URL_API_END_POINT_USERS = "/users"
const val URL_API_END_POINT_USERS_DETAILING = "/users/{username}"

interface RequestAPI {
    @GET(URL_API_END_POINT_USERS)
    fun getUsersGit(): Single<List<DTOListUsersGit>>


    @GET(URL_API_END_POINT_USERS_DETAILING)
    fun getDetailingUsersGit(
        @Path("username") username: String,
    ): Single<DTODetailingUserGit>

}