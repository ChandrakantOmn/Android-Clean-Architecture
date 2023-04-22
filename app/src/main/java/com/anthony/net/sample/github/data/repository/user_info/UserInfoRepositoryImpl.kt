package com.anthony.net.sample.github.data.repository.user_info

import com.anthony.net.sample.github.data.remote.Resource
import com.anthony.net.sample.github.data.remote.dto.login.Repository
import com.anthony.net.sample.github.data.remote.handleException
import com.anthony.net.sample.github.data.remote.service.user_info.UserInfoService
import com.anthony.net.sample.github.domain.repository.user_info.UserInfoRepository

class UserInfoRepositoryImpl(private val userInfoService: UserInfoService) : UserInfoRepository {

    override suspend fun getRepositories(loginName: String): Resource<List<Repository>> {
        return try {
            val data = userInfoService.getRepositories(loginName)
            Resource.Success(data)
        } catch (e: Exception) {
            handleException(e)
        }
    }

}