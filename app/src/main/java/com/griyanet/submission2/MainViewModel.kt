package com.griyanet.submission2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.griyanet.submission2.Model.Repository
import com.griyanet.submission2.Model.User
import androidx.lifecycle.viewModelScope
import retrofit2.Response
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository): ViewModel() {
    var myResponse: MutableLiveData<Response<ArrayList<User>>> = MutableLiveData()

    fun getUser() {
        viewModelScope.launch {
            val response = repository.getUser()
            myResponse.value = response
        }
    }
}