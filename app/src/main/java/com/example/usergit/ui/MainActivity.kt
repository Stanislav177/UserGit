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

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val adapterUsers = AdapterUsers()
    private val repoUsers: RepoUsers by lazy { app.userRepoUsers }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        showProgress(false)
        initRecycler()
        initViewBtn()
    }

    private fun initViewBtn() {
        binding.loadingUsersGitBtn.setOnClickListener {
            loadingUser()
        }
    }

    private fun showProgress(b: Boolean) {
        with(binding) {
            progressBar.isVisible = b
            recyclerUsersGit.isVisible = !b
        }

    }

    private fun loadingUser() {
        showProgress(true)
        repoUsers.getUsers(
            onSuccess = {
                onDataLoaded(it)
                showProgress(false)
            },
            onError = {
                showProgress(false)
                onError(it)

            }
        )
    }

    private fun onDataLoaded(data: List<UserEntity>) {
        adapterUsers.setUsersDataList(data)
    }

    private fun onError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }


    private fun initRecycler() {
        binding.recyclerUsersGit.adapter = adapterUsers
        binding.recyclerUsersGit.layoutManager = LinearLayoutManager(this)
    }
}