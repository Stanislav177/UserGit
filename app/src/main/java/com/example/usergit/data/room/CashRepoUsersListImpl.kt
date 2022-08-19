package com.example.usergit.data.room

import com.example.usergit.data.room.listUsers.HistoryUsersDao
import com.example.usergit.data.room.listUsers.HistoryUsersList
import com.example.usergit.domain.UserEntity
import com.example.usergit.domain.repos.usersList.RepoUsersListCash

class CashRepoUsersListImpl(private val cashHistoryUsersDao: HistoryUsersDao) : RepoUsersListCash {

    override fun getAllCash(): List<UserEntity> {
        return converterCashToUsersListEntity(cashHistoryUsersDao.all())
    }

    override fun saveListCash(list: List<HistoryUsersList>) {
        cashHistoryUsersDao.insert(list)
    }

    override fun deleteCash() {
        cashHistoryUsersDao.delete()
    }

    private fun converterCashToUsersListEntity(cashListUsers: List<HistoryUsersList>): List<UserEntity> {
        return cashListUsers.map { UserEntity(it.id, it.login, it.urlAvatar, it.urlUser) }
    }
}