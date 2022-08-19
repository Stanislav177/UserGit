package com.example.usergit

import com.example.usergit.data.room.CashRepoUsersListImpl
import com.example.usergit.data.room.listUsers.HistoryUsersDao
import com.example.usergit.data.room.listUsers.HistoryUsersList
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class CashRepoUsersListTest {

    private lateinit var cashRepoList: CashRepoUsersListImpl

    @Mock
    lateinit var cashHistoryUsersDao: HistoryUsersDao

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        cashRepoList = CashRepoUsersListImpl(cashHistoryUsersDao)
    }

    @Test
    fun testDeleteCash() {
        cashRepoList.deleteCash()
        verify(cashHistoryUsersDao, times(1)).delete()
    }

    @Test
    fun testGetAllCash() {
        cashRepoList.getAllCash()
        verify(cashHistoryUsersDao, times(1)).all()
    }

    @Test
    fun testSaveListCash() {
        val listMH = listOf(Mockito.mock(HistoryUsersList::class.java))
        cashRepoList.saveListCash(listMH)
        verify(cashHistoryUsersDao, times(1)).insert(listMH)
    }
}