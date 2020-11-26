package com.griyanet.submission2

import com.griyanet.submission2.api.RetrofitInstance
import com.griyanet.submission2.model.FollowersItem
import com.griyanet.submission2.model.UserDetails
import com.griyanet.submission2.model.UserItem
import com.griyanet.submission2.model.UserQuery
import retrofit2.Response

class Repository {

    suspend fun getUser(): Response<ArrayList<UserItem>> {
        return RetrofitInstance.api.getUser()
    }

    suspend fun getUserQuery(username: String): Response<UserQuery> {
        return RetrofitInstance.api.getUserQuery(username)
    }

    suspend fun getUserDetail(username: String): Response<UserDetails> {
        return RetrofitInstance.api.getUserDetail(username)
    }

    suspend fun getUserFollower(username: String): Response<ArrayList<FollowersItem>> {
        return RetrofitInstance.api.getUserFollower(username)
    }

    suspend fun getUserFollowing(username: String): Response<ArrayList<FollowersItem>> {
        return RetrofitInstance.api.getUserFollowing(username)
    }
}