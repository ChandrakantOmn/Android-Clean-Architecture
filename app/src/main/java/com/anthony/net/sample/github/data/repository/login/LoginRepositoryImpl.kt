package com.anthony.net.sample.github.data.repository.login

import com.anthony.net.sample.github.data.remote.Resource
import com.anthony.net.sample.github.data.remote.service.login.LoginService
import com.anthony.net.sample.github.domain.entity.common.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LoginRepositoryImpl(private val loginService: LoginService) : LoginRepository {
    override suspend fun getUser(userName: String): Flow<Resource<User>> = flow {
        emit(Resource.Loading)
        val originData = loginService.getUser(userName)
        val dataMapping = User(originData.login)
        emit(Resource.Success(dataMapping))
    }.catch {
        emit(Resource.Error(it.localizedMessage ?: "An unknown error occurred"))
    }.flowOn(Dispatchers.IO)

}