package com.griyanet.submission2

import com.griyanet.submission2.Api.RetrofitInstance
import com.griyanet.submission2.Model.UserDetails
import com.griyanet.submission2.Model.UserItem
import com.griyanet.submission2.Model.UserQuery
import retrofit2.Response

class Repository {

    suspend fun getUser(): Response<ArrayList<UserItem>> {
        return RetrofitInstance.api.getUser()
    }

    suspend fun getUserQuery(username: String): Response<UserQuery> {
        return RetrofitInstance.api.getUserQuery(username)
    }

    suspend fun getUserDetail(username: String): Response<ArrayList<UserDetails>> {
        return RetrofitInstance.api.getUserDetail(username)
    }

    suspend fun getUserFollower(username: String): Response<ArrayList<UserItem>> {
        return RetrofitInstance.api.getUserFollower(username)
    }

    suspend fun getUserFollowing(username: String): Response<ArrayList<UserItem>> {
        return RetrofitInstance.api.getUserFollowing(username)
    }
}