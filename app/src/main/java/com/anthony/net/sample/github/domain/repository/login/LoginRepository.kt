package com.anthony.net.sample.github.domain.repository.login

import com.anthony.net.sample.github.data.remote.Resource
import com.anthony.net.sample.github.data.remote.dto.common.User

interface LoginRepository {

    suspend fun getUser(userName: String): Resource<User>

}