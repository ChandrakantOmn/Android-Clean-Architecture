package com.anthony.net.sample.github.domain.repository.user_info

import com.anthony.net.sample.github.data.remote.Resource
import com.anthony.net.sample.github.data.remote.dto.user_info.Commit

interface CommitsRepository {

    suspend fun getCommits(
        userName: String, repoName: String
    ): Resource<List<Commit>>

}