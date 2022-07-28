package com.example.usergit.ui.detailingUser

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import coil.load
import com.example.dil.inject
import com.example.usergit.app
import com.example.usergit.databinding.ActivityDetailingUserBinding
import com.example.usergit.domain.UserDetailingEntity
import com.example.usergit.ui.detailingUser.appState.AppStateDetailingUser
import javax.inject.Inject

class DetailingUserActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailingUserBinding
    private var uri: Uri? = null
    private var loginUser: String? = null

    private val viewModel: DetailingViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailingUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //app.appComponent.inject(this)
        loginUser = intent.getStringExtra("KEY")
        initViewModel()
        onOpenPageUser()
    }

    private fun initViewModel() {
        viewModel.getLiveData().subscribe {
            renderData(it)
        }
        viewModel.error.subscribe {
            showError(it)
        }
        viewModel.startRequest(loginUser!!)
    }

    private fun renderData(appState: AppStateDetailingUser) {
        when (appState) {
            is AppStateDetailingUser.LoadingProgress -> {
                showProgress(appState.progress)
            }
            is AppStateDetailingUser.Success -> {
                showUser(appState.detailingUserGit)
            }
        }
    }

    private fun onOpenPageUser() {
        binding.onClickWebUser.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }

    private fun showError(t: Throwable) {
        Toast.makeText(this, t.message, Toast.LENGTH_SHORT).show()
    }

    private fun showUser(user: UserDetailingEntity) {
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

    private fun showProgress(b: Boolean) {
        with(binding) {
            inProgressBar!!.isVisible = b
        }
    }
}
