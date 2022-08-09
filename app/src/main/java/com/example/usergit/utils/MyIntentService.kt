package com.example.usergit.utils

import android.app.IntentService
import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.dil.inject
import com.example.usergit.data.DTODetailingUserGit
import com.example.usergit.data.retrofit.RequestAPI
import com.example.usergit.domain.UserDetailingEntity
import io.reactivex.rxjava3.kotlin.subscribeBy

class MyIntentService : IntentService("MyIntent") {

    private lateinit var loginUser: String
    private val B_CAST = "com.example.usergit.ui.detailingUser_BROAD"

    private val requestAPI: RequestAPI by inject()

    override fun onHandleIntent(intent: Intent?) {
        intent?.let {
            loginUser = it.getStringExtra("LOGIN")!!
        }
        getLoadingDetailingUser()
    }

    private fun getLoadingDetailingUser() {
        requestAPI.getDetailingUsersGit(loginUser).subscribeBy(
            onSuccess = {
                onSuccess(it)
            },
            onError = {
                onErrorLoading(it)
            }
        )
    }

    private fun onErrorLoading(it: Throwable) {
        TODO("Not yet implemented")
    }

    private fun onSuccess(it: DTODetailingUserGit) {
        val i = Intent()
        i.action = B_CAST
        i.putExtra("DTO", converterDtoToEntity(it))
        LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(i)
    }

    private fun converterDtoToEntity(dto: DTODetailingUserGit): UserDetailingEntity {
        return UserDetailingEntity(dto.login,
            dto.id,
            dto.avatarURL,
            dto.publicRepos,
            dto.url,
            dto.followers,
            dto.location,
            dto.name)
    }
}