package com.anthony.net.sample.github.client.service.login

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface LoginService {

    /**
     * 取得倉庫清單
     */
    @GET("users/{userName}/repos")
    suspend fun getUserRepositories(@Path("userName") userName: String): Response<ResponseBody>

}


