package com.example.usergit

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.usergit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val adapterUsers = AdapterUsers()
    private val repoUsers: RepoUsers = LocalRepoUsersImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecycler()
        showProgress(false)
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
                adapterUsers.setUsersDataList(it)
                showProgress(false)
            },
            onError = {
                showProgress(false)
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }
        )
    }

    private fun initRecycler() {
        binding.recyclerUsersGit.adapter = adapterUsers
        binding.recyclerUsersGit.layoutManager = LinearLayoutManager(this)
    }
}