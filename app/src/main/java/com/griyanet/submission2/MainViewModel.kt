package com.griyanet.submission2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.griyanet.submission2.Model.UserDetails
import com.griyanet.submission2.Model.UserItem
import com.griyanet.submission2.Model.UserQuery
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {

    var usersResponse: MutableLiveData<Response<ArrayList<UserItem>>> = MutableLiveData()
    var userQuery: MutableLiveData<Response<UserQuery>> = MutableLiveData()
    var userDetails: MutableLiveData<Response<ArrayList<UserDetails>>> = MutableLiveData()
    var userFollower: MutableLiveData<Response<ArrayList<UserItem>>> = MutableLiveData()
    var userFollowing: MutableLiveData<Response<ArrayList<UserItem>>> = MutableLiveData()

    fun getUser() {
        viewModelScope.launch {
            val response = repository.getUser()
            usersResponse.value = response
        }
    }

    fun getUserQuery(username: String) {
        viewModelScope.launch {
            val response = repository.getUserQuery(username)
            userQuery.value = response
        }
    }

    fun getUserDetail(username: String) {
        viewModelScope.launch {
            val response = repository.getUserDetail(username)
            userDetails.value = response
        }
    }

    fun getUserFollwer(username: String) {
        viewModelScope.launch {
            val response = repository.getUserFollower(username)
            userFollower.value = response
        }
    }

    fun getUserFollowing(username: String) {
        viewModelScope.launch {
            val response = repository.getUserFollowing(username)
            userFollowing.value = response
        }
    }
}