package com.griyanet.submission2.UI

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.griyanet.submission2.Model.UserItem
import retrofit2.Response

class UserViewModel : ViewModel() {

    var usersResponse: MutableLiveData<Response<ArrayList<UserItem>>> = MutableLiveData()


}