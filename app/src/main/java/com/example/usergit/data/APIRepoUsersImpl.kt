package com.example.usergit.data

import android.os.Handler
import android.os.Looper
import androidx.core.os.postDelayed
import com.example.usergit.DTOUsersGit
import com.example.usergit.domain.UserEntity
import com.example.usergit.domain.repos.RepoUsers
import com.example.usergit.retrofit.GitApiRequest
import com.example.usergit.retrofit.RetrofitAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class APIRepoUsersImpl : RepoUsers, GitApiRequest {

    private val requestAPI by lazy { RetrofitAPI.getStartRetrofitAPI() }

    private var data: List<UserEntity> = listOf()

    override fun getUsers(onSuccess: (List<UserEntity>) -> Unit, onError: ((Throwable) -> Unit)?) {
        Handler(Looper.getMainLooper()).postDelayed(3000L) {
            getUsersGitList(callback)
            onSuccess(data)
        }
    }

    override fun getUsersGitList(callback: Callback<DTOUsersGit>) {
        requestAPI.getUsersGit().enqueue(callback)
    }

    private val callback = object : Callback<DTOUsersGit> {
        override fun onResponse(call: Call<DTOUsersGit>, response: Response<DTOUsersGit>) {
            if (response.isSuccessful) {
                if (response.body() == null) {
                    //ERROR
                } else {
                    response.body()?.let {
                        data = converterDTOtoListUsers(it)
                    }
                }
            }
        }

        override fun onFailure(call: Call<DTOUsersGit>, t: Throwable) {
            val error = t.message
        }
    }

    private fun converterDTOtoListUsers(it: DTOUsersGit): List<UserEntity> {
        val list = mutableListOf<UserEntity>()
        val sizeDTO = it.users.size
        for (i in 0 until sizeDTO) {
            list.add(UserEntity(it.users[i][i].id, it.users[i][i].login, it.users[i][i].avatarURL))
        }
        return list

    }
}