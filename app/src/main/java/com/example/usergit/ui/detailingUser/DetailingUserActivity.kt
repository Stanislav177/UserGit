package com.example.usergit.ui.detailingUser

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.usergit.DTODetailingUserGit
import com.example.usergit.app
import com.example.usergit.databinding.ActivityDetailingUserBinding

class DetailingUserActivity : AppCompatActivity(), DetailingUserContract.View {

    lateinit var binding: ActivityDetailingUserBinding
    lateinit var presenter: DetailingUserContract.Presenter

    private var uri: Uri? = null
    private var loginUser: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailingUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loginUser = intent.getStringExtra("KEY")
        presenter = extractPresenter()
        presenter.loginUser(loginUser!!)
        presenter.attach(this)

        onOpenPageUser()
    }

    private fun extractPresenter(): DetailingUserContract.Presenter {
        return lastNonConfigurationInstance as? DetailingUserContract.Presenter
            ?: DetailingUserPresenter(app.repoUsersDetailing)
    }

    private fun onOpenPageUser() {
        binding.onClickWebUser.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }


    override fun showError(t: Throwable) {
        TODO("Not yet implemented")
    }

    override fun showUser(user: DTODetailingUserGit) {
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

    override fun showProgress(b: Boolean) {
        TODO("Not yet implemented")
    }

}
