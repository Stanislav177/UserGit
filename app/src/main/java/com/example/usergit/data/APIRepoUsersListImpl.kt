package com.example.usergit.data

import com.example.usergit.domain.UserEntity
import com.example.usergit.domain.repos.RepoUsersList
import com.example.usergit.data.retrofit.RetrofitAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class APIRepoUsersListImpl : RepoUsersList {

    private val requestAPI by lazy { RetrofitAPI.getStartRetrofitAPI() }

    override fun getUsersList(
        onSuccess: (List<UserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)?,
    ) {
        requestAPI.getUsersGit().enqueue(
            object : Callback<List<DTOListUsersGit>> {
                override fun onResponse(
                    call: Call<List<DTOListUsersGit>>,
                    response: Response<List<DTOListUsersGit>>,
                ) {
                    if (response.isSuccessful) {
                        if (response.body() == null) {
                            //ERROR
                        } else {
                            response.body()?.let {
                                onSuccess(converterDTOtoListUsers(it))
                                onError!!.invoke(Throwable("Error"))
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<List<DTOListUsersGit>>, t: Throwable) {
                    //ERROR
                }
            }
        )
    }

    private fun converterDTOtoListUsers(it: List<DTOListUsersGit>): List<UserEntity> {
        val list = mutableListOf<UserEntity>()
        val sizeDTO = it.size
        for (i in 0 until sizeDTO) {
            list.add(UserEntity(it[i].id, it[i].login, it[i].avatarURL, it[i].url))
        }
        return list

    }

}