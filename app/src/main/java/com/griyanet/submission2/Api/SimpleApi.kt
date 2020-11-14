package com.griyanet.submission2.Api

import com.griyanet.submission2.Model.User
import com.griyanet.submission2.Model.UserDetails
import retrofit2.Response
import retrofit2.http.GET

interface SimpleApi {

    @GET("User")
    suspend fun getUser(): Response<ArrayList<User>>

    @GET("UserDetails")
    suspend fun getUserDetails(): Response<UserDetails>

}