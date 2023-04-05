package com.anthony.net.sample.github.data.repository.user_info

import com.anthony.net.sample.github.data.remote.api.user_info.CommitsApi
import com.anthony.net.sample.github.data.remote.dto.user_info.Commit
import com.anthony.net.sample.github.domain.repository.user_info.CommitsRepository

class CommitsRepositoryImpl(private val commitsApi: CommitsApi) : CommitsRepository {

    override suspend fun getCommits(userName: String, repoName: String): List<Commit> {
        return commitsApi.getCommits(userName, repoName)
    }

}