package com.aragones.sergio.randomusersapp.userlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.aragones.sergio.randomusersapp.data.source.UserListRepository
import kotlinx.coroutines.flow.onEach

class UserListViewModel(
    private val repository: UserListRepository
) : ViewModel() {

    val loader = MutableLiveData<Boolean>()

    val users = liveData {

        loader.postValue(true)
        emitSource(
            repository.getUsers()
                .onEach { loader.postValue(false) }
                .asLiveData()
        )
    }
}