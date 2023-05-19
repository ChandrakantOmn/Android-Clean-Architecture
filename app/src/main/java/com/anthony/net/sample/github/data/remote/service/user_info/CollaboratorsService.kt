package com.anthony.net.sample.github.data.remote.service.user_info

import com.anthony.net.sample.github.data.remote.dto.user_info.CollaboratorDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CollaboratorsService {

    /**
     * 取得合作人列表
     */
    @GET("repos/{owner}/{repo}/collaborators")
    suspend fun getCollaborators(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): List<CollaboratorDto>


}