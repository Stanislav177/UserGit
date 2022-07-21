package com.example.usergit.ui.listUsers

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.usergit.databinding.ActivityMainBinding
import com.example.usergit.domain.UserEntity
import com.example.usergit.ui.detailingUser.DetailingUserActivity
import com.example.usergit.ui.utils.RxClick
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), OnClickListenerUser {

    lateinit var binding: ActivityMainBinding
    private val adapterUsers = UsersAdapter(this)
    private val viewModelDisposable = CompositeDisposable()
    private val viewModelUsers: UsersViewModel by viewModel()

    private val rxButton by lazy {
        binding.loadingUsersGitBtn
    }

    private val rxClick = object : RxClick {
        override fun rxOnClick(v: View) {
            viewModelUsers.onRefresh()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        initViewModel()
    }

    private fun initViewModel() {

        viewModelDisposable.addAll(
            viewModelUsers.usersLiveData.subscribe() {
                showUsers(it)
            },
            viewModelUsers.errorLiveData.subscribe() {
                showError(it)
            },
            viewModelUsers.progressLiveData.subscribe() {
                showProgress(it)
            }
        )
    }

    override fun onDestroy() {
        viewModelDisposable.dispose()
        rxButton.stop()
        super.onDestroy()
    }

    private fun initViews() {
        showProgress(false)
        initRecycler()
        initViewBtn()
    }

    private fun initViewBtn() {
        rxButton.rxBtnClickListener(rxButton, rxClick)
    }

    private fun showProgress(b: Boolean) {
        with(binding) {
            progressBar.isVisible = b
            recyclerUsersGit.isVisible = !b
        }
    }

    private fun showUsers(data: List<UserEntity>) {
        adapterUsers.setUsersDataList(data)
    }

    private fun showError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
        viewModelUsers.onLoadingCash()
    }

    private fun initRecycler() {
        binding.recyclerUsersGit.adapter = adapterUsers
        binding.recyclerUsersGit.layoutManager = LinearLayoutManager(this)
    }

    override fun onClick(loginUser: String) {
        Toast.makeText(this, loginUser, Toast.LENGTH_SHORT).show()
        val intent = Intent(this, DetailingUserActivity::class.java)
        intent.putExtra("KEY", loginUser)
        startActivity(intent)
    }
}