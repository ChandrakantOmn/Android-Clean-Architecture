package com.anthony.net.sample.github.data.repository.user_info

import com.anthony.net.sample.github.data.remote.Resource
import com.anthony.net.sample.github.domain.entity.login.Repository
import kotlinx.coroutines.flow.Flow

interface UserInfoRepository {

    suspend fun getRepositories(
        loginName: String
    ): Flow<Resource<List<Repository>>>

}