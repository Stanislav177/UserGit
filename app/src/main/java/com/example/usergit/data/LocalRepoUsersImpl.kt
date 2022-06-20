package com.example.usergit.data

import android.os.Handler
import android.os.Looper
import androidx.core.os.postDelayed
import com.example.usergit.domain.UserDetailing
import com.example.usergit.domain.UserEntity
import com.example.usergit.domain.repos.RepoUsers

class LocalRepoUsersImpl : RepoUsers {

    private val data: List<UserEntity> = listOf(
        UserEntity(1,
            "mojombo",
            "https://avatars.githubusercontent.com/u/1?v=4",
            "https://github.com/mojombo", UserDetailing("Tom Preston-Werner", "San Francisco", 64)),
        UserEntity(2,
            "defunkt",
            "https://avatars.githubusercontent.com/u/2?v=4",
            "https://github.com/defunkt", UserDetailing("Chris Wanstrath", "Неизвестно", 107)),
        UserEntity(3,
            "pjhyett",
            "https://avatars.githubusercontent.com/u/3?v=4",
            "https://github.com/pjhyett", UserDetailing("PJ Hyett", "San Francisco", 8))
    )

    override fun getUsers(onSuccess: (List<UserEntity>) -> Unit, onError: ((Throwable) -> Unit)?) {
        Handler(Looper.getMainLooper()).postDelayed(3000L) {
            onSuccess(data)
            //onError?.invoke(Throwable("Ошибка"))
        }
    }
}