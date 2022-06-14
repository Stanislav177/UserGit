package com.example.usergit

import android.os.Handler
import android.os.Looper
import androidx.core.os.postDelayed

class LocalRepoUsersImpl : RepoUsers {

    private val data: List<UserEntity> = listOf(
        UserEntity(1, "mojombo", "https://avatars.githubusercontent.com/u/1?v=4"),
        UserEntity(2, "defunkt", "https://avatars.githubusercontent.com/u/2?v=4"),
        UserEntity(3, "pjhyett", "https://avatars.githubusercontent.com/u/3?v=4")
    )

    override fun getUsers(onSuccess: (List<UserEntity>) -> Unit, onError: ((Throwable) -> Unit)?) {
        Handler(Looper.getMainLooper()).postDelayed(3000L) {
            onSuccess(data)
        }
    }
}