package com.anthony.net.sample.github.data.repository.login

import com.anthony.net.sample.github.data.remote.api.login.LoginApi
import com.anthony.net.sample.github.data.remote.dto.common.User
import com.anthony.net.sample.github.domain.repository.login.LoginRepository

class LoginRepositoryImpl(private val loginApi: LoginApi) : LoginRepository {

    override suspend fun getUser(userName: String): User {

        return loginApi.getUser(userName)

    }

}