package com.anthony.net.sample.github.data.repository.user_info

import com.anthony.net.sample.github.data.remote.model.user_info.CommitDto
import com.anthony.net.sample.github.data.remote.network.Resource
import com.anthony.net.sample.github.data.remote.service.user_info.CommitsService
import com.anthony.net.sample.github.domain.model.user_info.Commit
import com.anthony.net.sample.github.domain.repository.user_info.CommitsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CommitsRepositoryImpl(private val commitsService: CommitsService) : CommitsRepository {

    override suspend fun getCommits(
        userName: String,
        repoName: String
    ): Flow<Resource<List<Commit>>> = flow {
        emit(Resource.Loading)
        val originData = commitsService.getCommits(userName, repoName)
        val dataMapping = originData.map { commitDto -> mapCommitDtoToCommit(commitDto) }
        emit(Resource.Success(dataMapping))
    }.catch {
        emit(Resource.Error(it.localizedMessage ?: "An unknown error occurred"))
    }.flowOn(Dispatchers.IO)

}


private fun mapCommitDtoToCommit(commitDto: CommitDto): Commit {

    return Commit(
        nodeId = commitDto.node_id,
        userName = commitDto.commit.committer.name,
        date = commitDto.commit.committer.date,
        message = commitDto.commit.message
    )

}

