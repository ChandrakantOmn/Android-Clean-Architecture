package com.anthony.net.sample.github.domain.usecase.user_info

import com.anthony.net.sample.github.data.remote.network.Resource
import com.anthony.net.sample.github.domain.model.user_info.Commit
import com.anthony.net.sample.github.domain.repository.user_info.CommitsRepository
import kotlinx.coroutines.flow.Flow

class GetCommitsUseCase(private val commitsRepository: CommitsRepository) {

    suspend operator fun invoke(userName: String, repoName: String): Flow<Resource<List<Commit>>> {
        return commitsRepository.getCommits(userName, repoName)
    }

}