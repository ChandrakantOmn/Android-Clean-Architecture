package com.anthony.net.sample.github.domain.repository.user_info

import com.anthony.net.sample.github.data.remote.network.Resource
import com.anthony.net.sample.github.domain.model.login.Repository
import kotlinx.coroutines.flow.Flow

interface UserInfoRepository {

    suspend fun getRepositories(
        loginName: String
    ): Flow<Resource<List<Repository>>>

}