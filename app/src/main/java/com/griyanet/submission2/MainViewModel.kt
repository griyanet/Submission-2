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

    fun getUserQuery() {
        viewModelScope.launch {
            val response = repository.getUserQuery()
            userQuery.value = response
        }
    }

    fun getUserDetail() {
        viewModelScope.launch {
            val response = repository.getUserDetail()
            userDetails.value = response
        }
    }

    fun getUserFollwer() {
        viewModelScope.launch {
            val response = repository.getUserFollower()
            userFollower.value = response
        }
    }

    fun getUserFollowing() {
        viewModelScope.launch {
            val response = repository.getUserFollowing()
            userFollowing.value = response
        }
    }
}