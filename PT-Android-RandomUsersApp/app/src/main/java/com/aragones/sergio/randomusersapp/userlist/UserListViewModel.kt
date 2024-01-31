package com.aragones.sergio.randomusersapp.userlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aragones.sergio.randomusersapp.data.source.UserListRepository
import com.aragones.sergio.randomusersapp.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val repository: UserListRepository
) : ViewModel() {

    private val _loader = MutableLiveData<Boolean>()
    private val _users = MutableLiveData<MutableList<User>>(mutableListOf())
    private val _newUsers = MutableLiveData<List<User>>(listOf())
    private val _error = MutableLiveData<Throwable?>()
    private var page = 1
    private val results = 50

    val loader: LiveData<Boolean> = _loader
    val users: LiveData<MutableList<User>> = _users
    val newUsers: LiveData<List<User>> = _newUsers
    val error: LiveData<Throwable?> = _error
    var query = ""

    fun loadUsers() = viewModelScope.launch {

        _loader.postValue(true)
        repository.getUsers(page, results)
            .onEach { _loader.postValue(false) }
            .firstOrNull()?.apply {
                if (isSuccess) {

                    val result = getOrNull() ?: listOf()
                    _newUsers.postValue(
                        result.filter {
                            it.name.contains(query, true)
                                    || it.email.contains(query, true)
                        }
                    )
                    val value = _users.value ?: mutableListOf()
                    value.addAll(getOrNull() ?: listOf())
                    _users.postValue(value)
                    page += 1
                } else {
                    _error.postValue(exceptionOrNull())
                }
            }
    }

    fun reloadData() {

        page = 1
        _users.postValue(mutableListOf())
        _newUsers.postValue(mutableListOf())
    }

    fun setSearch(query: String) {
        this.query = query
    }
}