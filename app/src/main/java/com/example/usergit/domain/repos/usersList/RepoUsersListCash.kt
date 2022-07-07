package com.example.usergit.domain.repos.usersList

import com.example.usergit.domain.UserEntity

interface RepoUsersListCash {

    fun getAllCash(): List<UserEntity>
    fun saveListCash(list: List<UserEntity>)
    fun deleteCash()
}