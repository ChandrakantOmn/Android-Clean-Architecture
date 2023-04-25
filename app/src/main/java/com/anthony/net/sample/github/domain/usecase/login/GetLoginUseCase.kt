package com.anthony.net.sample.github.domain.usecase.login

import com.anthony.net.sample.github.data.remote.Resource
import com.anthony.net.sample.github.data.repository.login.LoginRepository
import com.anthony.net.sample.github.domain.entity.common.User
import kotlinx.coroutines.flow.Flow

class GetLoginUseCase(private val loginRepository: LoginRepository) {

    suspend operator fun invoke(userName: String): Flow<Resource<User>> {
        return loginRepository.getUser(userName)
    }

}