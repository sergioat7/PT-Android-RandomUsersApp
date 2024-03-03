package com.aragones.sergio.randomusersapp.userlist

import com.aragones.sergio.data.UserListRepository
import com.aragones.sergio.randomusersapp.utils.BaseUnitTest
import com.aragones.sergio.randomusersapp.utils.captureValues
import com.aragones.sergio.randomusersapp.utils.getValueForTest
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class UserListViewModelShould : BaseUnitTest() {

    private val repository: UserListRepository = mock()
    private val users: List<com.aragones.sergio.data.User> = listOf(
        mock<com.aragones.sergio.data.User>().also {
            whenever(it.name).thenReturn("")
            whenever(it.email).thenReturn("")
        },
        mock<com.aragones.sergio.data.User>().also {
            whenever(it.name).thenReturn("")
            whenever(it.email).thenReturn("")
        },
        mock<com.aragones.sergio.data.User>().also {
            whenever(it.name).thenReturn("")
            whenever(it.email).thenReturn("")
        }
    )
    private val expected = Result.success(users)
    private val exception = RuntimeException("Something went wrong")
    private lateinit var sut: UserListViewModel

    @Before
    fun setup() {
        sut = UserListViewModel(repository)
    }

    @Test
    fun `GIVEN success response WHEN get users from repository THEN do expected call`() {

        whenever(repository.getUsers(any(), any())).thenReturn(flow { emit(expected) })
        sut.loadUsers()

        sut.users.getValueForTest()

        verify(repository, times(1)).getUsers(any(), any())
    }

    @Test
    fun `GIVEN success response WHEN get users from repository THEN emits users`() = runTest {

        whenever(repository.getUsers(any(), any())).thenReturn(flow { emit(expected) })

        sut.loadUsers()

        assertEquals(expected.getOrNull(), sut.users.getValueForTest())
    }

    @Test
    fun `GIVEN network error WHEN get users from repository THEN emit error`() {

        whenever(repository.getUsers(any(), any())).thenReturn(flow { emit(Result.failure(exception)) })

        sut.loadUsers()

        assertEquals(exception, sut.error.getValueForTest())
    }

    @Test
    fun `GIVEN data loading WHEN get users from repository THEN show loader`() {

        whenever(repository.getUsers(any(), any())).thenReturn(flow { emit(expected) })

        sut.loader.captureValues {

            sut.loadUsers()

            assertEquals(true, values.first())
        }
    }

    @Test
    fun `GIVEN data loaded WHEN get users from repository THEN hide loader`() {

        whenever(repository.getUsers(any(), any())).thenReturn(flow { emit(expected) })

        sut.loader.captureValues {

            sut.loadUsers()

            assertEquals(false, values.last())
        }
    }

    @Test
    fun `GIVEN error received WHEN get users from repository THEN hide loader`() {

        whenever(repository.getUsers(any(), any())).thenReturn(flow { emit(Result.failure(exception)) })

        sut.loader.captureValues {

            sut.loadUsers()

            assertEquals(false, values.last())
        }

    }
}