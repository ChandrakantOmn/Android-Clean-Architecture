package com.anthony.net.sample.github.data.repository.login

import com.anthony.net.sample.github.data.remote.Resource
import com.anthony.net.sample.github.data.remote.dto.common.User
import com.anthony.net.sample.github.data.remote.handleException
import com.anthony.net.sample.github.data.remote.service.login.LoginService
import com.anthony.net.sample.github.domain.repository.login.LoginRepository

class LoginRepositoryImpl(private val loginService: LoginService) : LoginRepository {

    override suspend fun getUser(userName: String): Resource<User> {
        return try {
            val data = loginService.getUser(userName)
            Resource.Success(data)
        } catch (e: Exception) {
            handleException(e)
        }
    }

}