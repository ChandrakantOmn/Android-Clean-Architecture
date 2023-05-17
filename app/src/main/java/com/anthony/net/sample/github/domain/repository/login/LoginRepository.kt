package com.anthony.net.sample.github.domain.repository.login

import com.anthony.net.sample.github.data.remote.network.Resource
import com.anthony.net.sample.github.domain.model.common.User
import kotlinx.coroutines.flow.Flow


interface LoginRepository {

    suspend fun getUser(userName: String): Flow<Resource<User>>

}