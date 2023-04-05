package com.anthony.net.sample.github.domain.repository.user_info

import com.anthony.net.sample.github.data.remote.dto.login.Repository

interface UserInfoRepository {

    suspend fun getRepositories(
        loginName: String
    ): List<Repository>

}