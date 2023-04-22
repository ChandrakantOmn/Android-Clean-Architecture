package com.anthony.net.sample.github.data.repository.user_info

import com.anthony.net.sample.github.data.remote.Resource
import com.anthony.net.sample.github.data.remote.dto.user_info.Commit
import com.anthony.net.sample.github.data.remote.handleException
import com.anthony.net.sample.github.data.remote.service.user_info.CommitsService
import com.anthony.net.sample.github.domain.repository.user_info.CommitsRepository

class CommitsRepositoryImpl(private val commitsService: CommitsService) : CommitsRepository {

    override suspend fun getCommits(userName: String, repoName: String): Resource<List<Commit>> {
        return try {
            val data = commitsService.getCommits(userName, repoName)
            Resource.Success(data)
        } catch (e: Exception) {
            handleException(e)
        }
    }

}