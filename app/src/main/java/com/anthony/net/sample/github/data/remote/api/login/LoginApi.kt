package com.anthony.net.sample.github.data.remote.api.login

import com.anthony.net.sample.github.data.remote.dto.common.User
import retrofit2.http.GET
import retrofit2.http.Path

interface LoginApi {

    /**
     * 取得 User
     */
    @GET("users/{userName}")
    suspend fun getUser(@Path("userName") userName: String): User

}


