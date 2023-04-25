package com.anthony.net.sample.github.data.repository.login

import com.anthony.net.sample.github.data.remote.Resource
import com.anthony.net.sample.github.domain.entity.common.User
import kotlinx.coroutines.flow.Flow


interface LoginRepository {

    suspend fun getUser(userName: String): Flow<Resource<User>>

}