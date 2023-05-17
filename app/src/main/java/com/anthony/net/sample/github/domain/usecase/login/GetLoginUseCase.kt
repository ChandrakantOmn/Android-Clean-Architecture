package com.anthony.net.sample.github.domain.usecase.login

import com.anthony.net.sample.github.data.remote.network.Resource
import com.anthony.net.sample.github.domain.model.common.User
import com.anthony.net.sample.github.domain.repository.login.LoginRepository
import kotlinx.coroutines.flow.Flow

class GetLoginUseCase(private val loginRepository: LoginRepository) {

    suspend operator fun invoke(userName: String): Flow<Resource<User>> {
        return loginRepository.getUser(userName)
    }

}