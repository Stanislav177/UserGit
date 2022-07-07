package com.example.usergit.data

import android.os.Handler
import android.os.Looper
import androidx.core.os.postDelayed
import com.example.usergit.domain.UserEntity
import com.example.usergit.domain.repos.usersList.RepoUsersList
import io.reactivex.rxjava3.core.Single

class LocalRepoUsersImpl : RepoUsersList {

    private val data: List<UserEntity> = listOf(
        UserEntity(1,
            "mojombo",
            "https://avatars.githubusercontent.com/u/1?v=4",
            "https://github.com/mojombo"),
        UserEntity(2,
            "defunkt",
            "https://avatars.githubusercontent.com/u/2?v=4",
            "https://github.com/defunkt"),
        UserEntity(3,
            "pjhyett",
            "https://avatars.githubusercontent.com/u/3?v=4",
            "https://github.com/pjhyett")
    )

    override fun getUsersList(
        onSuccess: (List<UserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)?,
    ) {
        Handler(Looper.getMainLooper()).postDelayed(3000L) {
            onSuccess(data)
            //onError?.invoke(Throwable("Ошибка"))
        }
    }

    override fun getUsersList(): Single<List<UserEntity>> = Single.just(data)
}