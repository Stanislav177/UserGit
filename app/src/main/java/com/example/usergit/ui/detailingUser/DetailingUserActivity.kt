package com.example.usergit.ui.detailingUser

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.usergit.databinding.ActivityDetailingUserBinding
import com.example.usergit.domain.UserEntity

class DetailingUserActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailingUserBinding

    private var detailingUserData: UserEntity? = null

    private var uri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailingUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailingUserData =
            intent.getParcelableExtra("KEY")
        displayUserEntity()

        onOpenPageUser()
    }

    private fun onOpenPageUser() {
        binding.onClickWebUser.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }

    private fun displayUserEntity() {
        detailingUserData?.let {
            with(binding) {
                uri = Uri.parse(it.urlUser)
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