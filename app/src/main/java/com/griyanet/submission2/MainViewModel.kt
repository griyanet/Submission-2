package com.griyanet.submission2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.griyanet.submission2.Model.Repository
import com.griyanet.submission2.Model.UserItem
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {
    var usersResponse: MutableLiveData<Response<ArrayList<UserItem>>> = MutableLiveData()

    fun getUser() {
        viewModelScope.launch {
            val response = repository.getUser()
            usersResponse.value = response
        }
    }
}