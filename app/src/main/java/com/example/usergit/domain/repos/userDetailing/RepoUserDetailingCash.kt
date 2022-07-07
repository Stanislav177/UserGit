package com.example.usergit.domain.repos.userDetailing

import com.example.usergit.data.DTODetailingUserGit
import com.example.usergit.domain.UserDetailingEntity

interface RepoUserDetailingCash {
    fun getDetailingUserCash(): UserDetailingEntity
    fun saveDetailingCash(dtoUser: DTODetailingUserGit)
    fun deleteCash()
}