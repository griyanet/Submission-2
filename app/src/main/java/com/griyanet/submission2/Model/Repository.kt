package com.griyanet.submission2.Model

import com.griyanet.submission2.Api.RetrofitInstance
import retrofit2.Response

class Repository {
    suspend fun getUser(): Response<ArrayList<User>> {
        return RetrofitInstance.api.getUser()
    }
}