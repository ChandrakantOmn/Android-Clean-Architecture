package com.anthony.net.sample.github.data.remote.api.user_info

import com.anthony.net.sample.github.data.remote.dto.login.Repository
import retrofit2.http.GET
import retrofit2.http.Path

interface UserInfoApi {

    /**
     * 取得倉庫清單
     */
    @GET("users/{userName}/repos")
    suspend fun getRepositories(@Path("userName") userName: String): List<Repository>

}