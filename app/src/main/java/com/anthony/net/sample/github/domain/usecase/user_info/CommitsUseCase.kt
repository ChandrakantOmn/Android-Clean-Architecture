package com.anthony.net.sample.github.domain.usecase.user_info

import com.anthony.net.sample.github.data.remote.Resource
import com.anthony.net.sample.github.data.remote.dto.user_info.Commit
import com.anthony.net.sample.github.data.remote.handleException
import com.anthony.net.sample.github.data.repository.user_info.CommitsRepositoryImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CommitsUseCase(private val commitsRepository: CommitsRepositoryImpl) {

    suspend fun getCommits(
        userName: String, repoName: String
    ): Flow<Resource<List<Commit>>> = flow {

        try {

            val data = commitsRepository.getCommits(userName, repoName)

            emit(Resource.Success(data))

        } catch (e: Exception) {
            emit(handleException(e))
        }

    }


}