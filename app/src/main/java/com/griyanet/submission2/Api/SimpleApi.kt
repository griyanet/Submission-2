package com.griyanet.submission2.Api

import com.griyanet.submission2.Model.UserDetails
import com.griyanet.submission2.Model.UserItem
import com.griyanet.submission2.Model.UserQuery
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SimpleApi {

    @GET("users")
    suspend fun getUser(): Response<ArrayList<UserItem>>

    @GET("search/users")
    suspend fun getUserQuery(@Query("q") login: String): Response<UserQuery>

    @GET("users/{username}")
    suspend fun getUserDetail(@Path("username") username: String): Response<ArrayList<UserDetails>>

    @GET("users/{username}/followers")
    suspend fun getUserFollower(@Query("q") username: String): Response<ArrayList<UserItem>>

    @GET("users/{username}/following")
    suspend fun getUserFollowing(@Query("q") username: String): Response<ArrayList<UserItem>>

}