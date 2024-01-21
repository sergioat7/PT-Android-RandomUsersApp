package com.aragones.sergio.randomusersapp.data.source

import com.aragones.sergio.randomusersapp.model.User
import com.aragones.sergio.randomusersapp.network.UserListService
import com.aragones.sergio.randomusersapp.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class UserListRepositoryTest : BaseUnitTest() {

    private val service: UserListService = mock()
    private val users: List<User> = mock()
    private val expected = Result.success(users)
    private val exception = RuntimeException("Something went wrong")
    private lateinit var sut: UserListRepository

    @Before
    fun setup() {
        sut = UserListRepository(service)
    }

    @Test
    fun `GIVEN success response WHEN get users THEN do expected call`() {

        whenever(service.fetchUserList()).thenReturn(flow { emit(expected) })

        sut.getUsers()

        verify(service, times(1)).fetchUserList()
    }

    @Test
    fun `GIVEN success response WHEN get users THEN emit users from service`() = runTest {

        whenever(service.fetchUserList()).thenReturn(flow { emit(expected) })

        val result = sut.getUsers()

        assertEquals(users, result.first().getOrNull())
    }

    @Test
    fun `GIVEN network error WHEN get users THEN propagate error`() = runTest {

        whenever(service.fetchUserList()).thenReturn(flow { emit(Result.failure(exception)) })

        val result = sut.getUsers()

        assertEquals(exception, result.first().exceptionOrNull())
    }
}