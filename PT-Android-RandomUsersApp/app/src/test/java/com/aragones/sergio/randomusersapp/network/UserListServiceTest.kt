package com.aragones.sergio.randomusersapp.network

import com.aragones.sergio.randomusersapp.model.User
import com.aragones.sergio.randomusersapp.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class UserListServiceTest : BaseUnitTest() {

    private val api: UserApi = mock()
    private val users: List<User> = mock()
    private lateinit var sut: UserListService

    @Before
    fun setup() {
        sut = UserListService(api)
    }

    @Test
    fun `GIVEN success response WHEN fetch users THEN do expected call`() = runTest {

        whenever(api.fetchAllUsers()).thenReturn(users)

        sut.fetchUserList().first()

        verify(api, times(1)).fetchAllUsers()
    }

    @Test
    fun `GIVEN success response WHEN fetch users THEN emit users from api`() = runTest {

        whenever(api.fetchAllUsers()).thenReturn(users)

        val result = sut.fetchUserList()

        assertEquals(users, result.first().getOrNull())
    }

    @Test
    fun `GIVEN network error WHEN fetch users THEN emit error`() = runTest {

        whenever(api.fetchAllUsers()).thenThrow(RuntimeException("Backend error"))

        val result = sut.fetchUserList()

        assertEquals("Backend error", result.first().exceptionOrNull()?.message)
    }
}