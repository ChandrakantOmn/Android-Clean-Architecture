package com.anthony.net.sample.github.domain.usecase.user_info

import com.anthony.net.sample.github.data.remote.Resource
import com.anthony.net.sample.github.data.remote.dto.user_info.Commit
import com.anthony.net.sample.github.domain.repository.user_info.CommitsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CommitsUseCase(private val commitsRepository: CommitsRepository) {

    suspend fun getCommits(
        userName: String, repoName: String
    ): Flow<Resource<List<Commit>>> = flow {
        val result = commitsRepository.getCommits(userName, repoName)
        emit(result)
    }

}