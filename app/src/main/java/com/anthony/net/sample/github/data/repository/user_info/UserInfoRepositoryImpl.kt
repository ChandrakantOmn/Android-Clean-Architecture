package com.anthony.net.sample.github.data.repository.user_info

import com.anthony.net.sample.github.data.remote.api.user_info.UserInfoApi
import com.anthony.net.sample.github.data.remote.dto.login.Repository
import com.anthony.net.sample.github.domain.repository.user_info.UserInfoRepository

class UserInfoRepositoryImpl(private val userInfoApi: UserInfoApi) : UserInfoRepository {

    override suspend fun getRepositories(loginName: String): List<Repository> {
        return userInfoApi.getRepositories(loginName)
    }

}