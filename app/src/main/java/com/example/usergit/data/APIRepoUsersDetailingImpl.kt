package com.example.usergit.data

import com.example.usergit.data.retrofit.RetrofitAPI
import com.example.usergit.domain.repos.RepoUserDetailing
import com.example.usergit.ui.detailingUser.appState.AppStateDetailingUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class APIRepoUsersDetailingImpl : RepoUserDetailing {

    private val requestAPI by lazy { RetrofitAPI.getStartRetrofitAPI() }

    private lateinit var loginUser: String

    override fun getDetailingUser(
        onSuccess: (AppStateDetailingUser) -> Unit,
        onError: ((Throwable) -> Unit)?
    ) {
        requestAPI.getDetailingUsersGit(loginUser).enqueue(object : Callback<DTODetailingUserGit> {
            override fun onResponse(
                call: Call<DTODetailingUserGit>,
                response: Response<DTODetailingUserGit>,
            ) {
                if (response.isSuccessful) {
                    if (response.body() == null) {
                        //ERROR
                    } else {
                        response.body()?.let {
                            onSuccess(AppStateDetailingUser.Success(it))
                            onError!!.invoke(Throwable("JJJJJJJJJJJ"))
                        }
                    }

                } else {

                }
            }

            override fun onFailure(call: Call<DTODetailingUserGit>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    override fun getLoginUser(login: String) {
        loginUser = login
    }
}