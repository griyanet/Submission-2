package com.griyanet.submission2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.griyanet.submission2.model.UserDetails
import com.griyanet.submission2.model.UserItem
import com.griyanet.submission2.model.UserQuery
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {

    private val _usersResponse: MutableLiveData<Response<ArrayList<UserItem>>> = MutableLiveData()
    val usersResponse: LiveData<Response<ArrayList<UserItem>>>
        get() = _usersResponse

    private val _userQuery: MutableLiveData<Response<UserQuery>> = MutableLiveData()
    val userQuery: LiveData<Response<UserQuery>>
        get() = _userQuery

    private val _userDetails: MutableLiveData<Response<ArrayList<UserDetails>>> = MutableLiveData()
    val userDetails: LiveData<Response<ArrayList<UserDetails>>>
        get() = _userDetails

    private val _userFollower: MutableLiveData<Response<ArrayList<UserItem>>> = MutableLiveData()
    val userFollower: LiveData<Response<ArrayList<UserItem>>>
        get() = _userFollower

    private val _userFollowing: MutableLiveData<Response<ArrayList<UserItem>>> = MutableLiveData()
    val userFollowing: LiveData<Response<ArrayList<UserItem>>>
        get() = _userFollowing

    val loading: MutableLiveData<Boolean> = MutableLiveData()

    init {


    }

    fun getUser() {
        loading.value = true
        viewModelScope.launch {
            val response = repository.getUser()
            _usersResponse.value = response
            loading.value = false
        }
    }

    fun getUserQuery(username: String) {
        loading.value = true
        viewModelScope.launch {
            val response = repository.getUserQuery(username)
            _userQuery.value = response
            loading.value = false
        }
    }

    fun getUserDetail(username: String) {
        viewModelScope.launch {
            val response = repository.getUserDetail(username)
            _userDetails.value = response
        }
    }

    fun getUserFollower(username: String) {
        viewModelScope.launch {
            val response = repository.getUserFollower(username)
            _userFollower.value = response
        }
    }

    fun getUserFollowing(username: String) {
        viewModelScope.launch {
            val response = repository.getUserFollowing(username)
            _userFollowing.value = response
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}