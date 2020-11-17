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

    suspend fun getUserQuery(): Response<UserQuery> {
        return RetrofitInstance.api.getUserQuery(username = String())
    }

    suspend fun getUserDetail(): Response<ArrayList<UserDetails>> {
        return RetrofitInstance.api.getUserDetail(username = String())
    }

    suspend fun getUserFollower(): Response<ArrayList<UserItem>> {
        return RetrofitInstance.api.getUserFollower(username = String())
    }

    suspend fun getUserFollowing(): Response<ArrayList<UserItem>> {
        return RetrofitInstance.api.getUserFollowing(username = String())
    }
}