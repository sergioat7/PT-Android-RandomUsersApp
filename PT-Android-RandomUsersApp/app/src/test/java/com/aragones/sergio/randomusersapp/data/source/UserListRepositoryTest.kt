package com.aragones.sergio.randomusersapp.data.source

import com.aragones.sergio.randomusersapp.model.User
import com.aragones.sergio.domain.UserRaw
import com.aragones.sergio.randomusersapp.network.UserListMapper
import com.aragones.sergio.randomusersapp.network.UserListService
import com.aragones.sergio.randomusersapp.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.any
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
    private val mapper: UserListMapper = mock()
    private val users: List<User> = mock()
    private val usersRaw: List<UserRaw> = mock()
    private val exception = RuntimeException("Something went wrong")
    private lateinit var sut: UserListRepository

    @Before
    fun setup() {
        sut = UserListRepository(service, mapper)
    }

    @Test
    fun `GIVEN success response WHEN get users THEN do expected call`() {

        whenever(service.fetchUserList(any(), any())).thenReturn(flow { emit(Result.success(usersRaw)) })
        whenever(mapper.invoke(usersRaw)).thenReturn(users)

        sut.getUsers(any(), any())

        verify(service, times(1)).fetchUserList(any(), any())
    }

    @Test
    fun `GIVEN success response WHEN get users THEN emit users from service`() = runTest {

        whenever(service.fetchUserList(any(), any())).thenReturn(flow { emit(Result.success(usersRaw)) })
        whenever(mapper.invoke(usersRaw)).thenReturn(users)

        val result = sut.getUsers(any(), any())

        assertEquals(users, result.first().getOrNull())
    }

    @Test
    fun `GIVEN network error WHEN get users THEN propagate error`() = runTest {

        whenever(service.fetchUserList(any(), any())).thenReturn(flow { emit(Result.failure(exception)) })
        whenever(mapper.invoke(usersRaw)).thenReturn(users)

        val result = sut.getUsers(any(), any())

        assertEquals(exception, result.first().exceptionOrNull())
    }

    @Test
    fun `GIVEN users raw WHEN get users THEN map to users`() = runTest {

        whenever(service.fetchUserList(any(), any())).thenReturn(flow { emit(Result.success(usersRaw)) })
        whenever(mapper.invoke(usersRaw)).thenReturn(users)

        sut.getUsers(any(), any()).first()

        verify(mapper, times(1)).invoke(usersRaw)
    }
}