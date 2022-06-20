package com.example.usergit.ui.detailingUser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.usergit.databinding.ActivityDetailingUserBinding
import com.example.usergit.domain.UserDetailing
import com.example.usergit.domain.UserEntity

class DetailingUserActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailingUserBinding

    private var detailingUserData: UserEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailingUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailingUserData =
            intent.getParcelableExtra("KEY") ?: UserEntity(0,
                " ",
                " ",
                " ",
                UserDetailing(" ", " ", 0))



        displayUserEntity()
    }

    private fun displayUserEntity() {
        detailingUserData?.let {
            with(binding) {
                nameUserDetailingActivity.text = it.detailing.name
                loginUserDetailingActivity.text = it.nickname
                idUserDetailingActivity.text = it.id.toString()
                locationUserDetailingActivity.text = it.detailing.location
                publicRepoUserDetailingActivity.text = it.detailing.publicRepos.toString()
                userAvatarDetailingActivity.load(it.urlAvatar)
            }
        }
    }
}