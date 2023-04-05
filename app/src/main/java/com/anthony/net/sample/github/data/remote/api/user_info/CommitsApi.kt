package com.anthony.net.sample.github.data.remote.api.user_info

import com.anthony.net.sample.github.data.remote.dto.user_info.Commit
import retrofit2.http.GET
import retrofit2.http.Path

interface CommitsApi {

    /**
     * 取得提交的訊息列表
     */
    @GET("repos/{owner}/{repo}/commits")
    suspend fun getCommits(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): List<Commit>

}