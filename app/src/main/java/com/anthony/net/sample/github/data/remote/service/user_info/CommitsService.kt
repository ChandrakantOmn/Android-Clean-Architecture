package com.anthony.net.sample.github.data.remote.service.user_info

import com.anthony.net.sample.github.data.remote.model.user_info.CommitDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CommitsService {

    /**
     * 取得提交的訊息列表
     */
    @GET("repos/{owner}/{repo}/commits")
    suspend fun getCommits(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): List<CommitDto>

}