package com.example.usergit.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.usergit.app
import com.example.usergit.databinding.ActivityMainBinding
import com.example.usergit.domain.UserEntity
import com.example.usergit.domain.repos.RepoUsers

class MainActivity : AppCompatActivity(), UserContract.View {

    lateinit var binding: ActivityMainBinding

    private val adapterUsers = UsersAdapter()
    private val repoUsers: RepoUsers by lazy { app.userRepoUsers }

    private lateinit var presenter: UserContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        presenter = UserPresenter(app.userRepoUsers)
        presenter.attach(this)
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    private fun initViews() {
        showProgress(false)
        initRecycler()
        initViewBtn()
    }

    private fun initViewBtn() {
        binding.loadingUsersGitBtn.setOnClickListener {
        presenter.onRefresh()
        }
    }

    override fun showProgress(b: Boolean) {
        with(binding) {
            progressBar.isVisible = b
            recyclerUsersGit.isVisible = !b
        }
    }

    override fun showUsers(data: List<UserEntity>) {
        adapterUsers.setUsersDataList(data)
    }

    override fun showError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }

    private fun initRecycler() {
        binding.recyclerUsersGit.adapter = adapterUsers
        binding.recyclerUsersGit.layoutManager = LinearLayoutManager(this)
    }
}