package com.example.usergit.data.room

import com.example.usergit.domain.UserEntity
import com.example.usergit.domain.repos.usersList.RepoUsersListCash
import com.example.usergit.data.room.listUsers.HistoryUsersDao
import com.example.usergit.data.room.listUsers.HistoryUsersList

class CashRepoUsersListImpl(private val cashListUsers: HistoryUsersDao) : RepoUsersListCash {

    override fun getAllCash(): List<UserEntity> {
        return converterCashToUsersListEntity(cashListUsers.all())
    }

    override fun saveListCash(list: List<UserEntity>) {
        cashListUsers.insert(converterListEntityToCash(list))
    }

    override fun deleteCash() {
        cashListUsers.delete()
    }


    private fun converterCashToUsersListEntity(cashListUsers: List<HistoryUsersList>): List<UserEntity> {
        return cashListUsers.map { UserEntity(it.id, it.login, it.urlAvatar, it.urlUser) }
    }

    private fun converterListEntityToCash(listUsers: List<UserEntity>): List<HistoryUsersList> {
        return listUsers.map { HistoryUsersList(it.id, it.login, it.urlAvatar, it.urlUser) }
    }

}