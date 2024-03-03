package com.aragones.sergio.randomusersapp.network

import com.aragones.sergio.domain.InfoRaw
import com.aragones.sergio.domain.ResultsRaw
import com.aragones.sergio.domain.UserRaw
import com.aragones.sergio.network.UserApi
import com.aragones.sergio.network.UserListService
import com.aragones.sergio.randomusersapp.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.any
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
    private val users: List<UserRaw> = mock()
    private val expected = ResultsRaw(users, InfoRaw(0, 0))
    private lateinit var sut: UserListService

    @Before
    fun setup() {
        sut = UserListService(api)
    }

    @Test
    fun `GIVEN success response WHEN fetch users THEN do expected call`() = runTest {

        whenever(api.fetchAllUsers(any(), any(), any())).thenReturn(expected)

        sut.fetchUserList(1, 1).first()

        verify(api, times(1)).fetchAllUsers(any(), any(), any())
    }

    @Test
    fun `GIVEN success response WHEN fetch users THEN emit users from api`() = runTest {

        whenever(api.fetchAllUsers(any(), any(), any())).thenReturn(expected)

        val result = sut.fetchUserList(1, 1)

        assertEquals(users, result.first().getOrNull())
    }

    @Test
    fun `GIVEN network error WHEN fetch users THEN emit error`() = runTest {

        whenever(api.fetchAllUsers(any(), any(), any())).thenThrow(RuntimeException("Backend error"))

        val result = sut.fetchUserList(1, 1)

        assertEquals("Backend error", result.first().exceptionOrNull()?.message)
    }
}