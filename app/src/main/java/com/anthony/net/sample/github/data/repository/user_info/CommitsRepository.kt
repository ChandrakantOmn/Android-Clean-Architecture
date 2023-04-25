package com.anthony.net.sample.github.data.repository.user_info

import com.anthony.net.sample.github.data.remote.Resource
import com.anthony.net.sample.github.domain.entity.user_info.Commit
import kotlinx.coroutines.flow.Flow

interface CommitsRepository {

    suspend fun getCommits(
        userName: String,
        repoName: String
    ): Flow<Resource<List<Commit>>>

}