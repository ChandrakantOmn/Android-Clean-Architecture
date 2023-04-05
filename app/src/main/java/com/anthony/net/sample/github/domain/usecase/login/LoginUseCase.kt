package com.anthony.net.sample.github.domain.usecase.login

import com.anthony.net.sample.github.data.remote.Resource
import com.anthony.net.sample.github.data.remote.dto.common.User
import com.anthony.net.sample.github.data.remote.handleException
import com.anthony.net.sample.github.data.repository.login.LoginRepositoryImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginUseCase(private val loginRepository: LoginRepositoryImpl) {

    suspend fun getUser(userName: String): Flow<Resource<User>> = flow {

        try {

            val data = loginRepository.getUser(userName)

            emit(Resource.Success(data))

        } catch (e: Exception) {
            emit(handleException(e))
        }

    }

}