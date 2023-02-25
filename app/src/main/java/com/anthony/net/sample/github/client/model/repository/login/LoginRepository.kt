package com.anthony.net.sample.github.client.model.repository.login

import com.anthony.net.sample.github.client.dto.common.Error
import com.anthony.net.sample.github.client.dto.common.User
import com.anthony.net.sample.github.client.network.Resource
import com.anthony.net.sample.github.client.network.RetrofitBuilder
import com.anthony.net.sample.github.client.service.login.LoginService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.decodeFromString
import retrofit2.HttpException
import java.io.IOException

class LoginRepository(private val loginService: LoginService) {

    suspend fun getUser(userName: String): Flow<Resource<User>> = flow {

        try {

            val data = loginService.getUser(userName)

            emit(Resource.Success(data))

        } catch (e: HttpException) {

            val errorMessage = e.response()?.errorBody()?.let {
                RetrofitBuilder.json.decodeFromString<Error>(it.string()).message
            } ?: kotlin.run {
                null
            }

            emit(Resource.Error(errorMessage ?: "An unexpected error occurred"))

        } catch (e: IOException) {

            emit(Resource.Error("Couldn't reach server. Check your internet connection."))

        }

    }

}