package com.griyanet.submission2.Api

import com.griyanet.submission2.Model.UserItem
import retrofit2.Response
import retrofit2.http.GET

interface SimpleApi {

    @GET("users")
    suspend fun getUser(): Response<ArrayList<UserItem>>

    //@GET("UserDetails")
    //suspend fun getUserDetails(): Response<UserDetails>

}