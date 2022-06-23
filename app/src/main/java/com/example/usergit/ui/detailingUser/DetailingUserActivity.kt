package com.example.usergit.ui.detailingUser

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.usergit.DTODetailingUserGit
import com.example.usergit.databinding.ActivityDetailingUserBinding

class DetailingUserActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailingUserBinding
    private var uri: Uri? = null
    private var loginUser: String? = null

    private val liveData: DetailingViewModel by lazy {
        ViewModelProvider(this).get(DetailingViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailingUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loginUser = intent.getStringExtra("KEY")

        liveData.getLiveData().observe(this, {
            renderData(it)
        })
        liveData.loading(loginUser!!)

        onOpenPageUser()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {

            }
            is AppState.LoadingProgress -> {

            }
            is AppState.Success -> {
                showUser(appState.dtoDetailingUserGit)
            }
        }
    }

    private fun onOpenPageUser() {
        binding.onClickWebUser.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }


    fun showError(t: Throwable) {
        TODO("Not yet implemented")
    }

    private fun showUser(user: DTODetailingUserGit) {
        with(binding) {
            uri = Uri.parse(user.url)
            nameUserDetailingActivity.text = user.name
            loginUserDetailingActivity.text = user.login
            idUserDetailingActivity.text = user.id.toString()
            locationUserDetailingActivity.text = user.location
            publicRepoUserDetailingActivity.text = user.publicRepos.toString()
            userAvatarDetailingActivity.load(user.avatarURL)
        }

    }

    fun showProgress(b: Boolean) {
        TODO("Not yet implemented")
    }

}
