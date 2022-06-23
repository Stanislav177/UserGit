package com.example.usergit.data

import com.example.usergit.DTODetailingUserGit
import com.example.usergit.domain.repos.RepoUsersDetailing
import com.example.usergit.retrofit.RetrofitAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class APIRepoUsersDetailingImpl : RepoUsersDetailing {

    private val requestAPI by lazy { RetrofitAPI.getStartRetrofitAPI() }

    private var loginUser: String? = null

    override fun getUsers(
        onSuccess: (DTODetailingUserGit) -> Unit,
        onError: ((Throwable) -> Unit)?,
    ) {
        requestAPI.getDetailingUsersGit(loginUser!!)
            .enqueue(object : Callback<DTODetailingUserGit> {
                override fun onResponse(
                    call: Call<DTODetailingUserGit>,
                    response: Response<DTODetailingUserGit>,
                ) {
                    if (response.isSuccessful) {
                        if (response.body() == null) {
                            //Error
                        } else {
                            response.body()?.let {
                                onSuccess(it)
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<DTODetailingUserGit>, t: Throwable) {
                    val error = t.message
                }
            })
    }

    override fun setLoginUser(login: String) {
        loginUser = login
    }
}