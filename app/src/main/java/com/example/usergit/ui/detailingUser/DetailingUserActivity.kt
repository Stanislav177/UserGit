package com.example.usergit.ui.detailingUser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.usergit.databinding.ActivityDetailingUserBinding
import com.example.usergit.domain.UserEntity

class DetailingUserActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailingUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailingUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = intent.getParcelableExtra<UserEntity>("KEY")
        binding.nameUserDetailingActivity.text = user!!.detailing.name
        binding.loginUserDetailingActivity.text = user.nickname
        binding.idUserDetailingActivity.text = user.id.toString()
        binding.locationUserDetailingActivity.text = user.detailing.location
        binding.publicRepoUserDetailingActivity.text = user.detailing.publicRepos.toString()
        binding.userAvatarDetailingActivity.load(user.urlAvatar)
    }
}