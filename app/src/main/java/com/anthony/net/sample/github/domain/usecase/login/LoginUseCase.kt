package com.anthony.net.sample.github.domain.usecase.login

import com.anthony.net.sample.github.data.remote.Resource
import com.anthony.net.sample.github.data.remote.dto.common.User
import com.anthony.net.sample.github.domain.repository.login.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginUseCase(private val loginRepository: LoginRepository) {

    suspend fun getUser(userName: String): Flow<Resource<User>> = flow {
        val result = loginRepository.getUser(userName)
        emit(result)
    }

}