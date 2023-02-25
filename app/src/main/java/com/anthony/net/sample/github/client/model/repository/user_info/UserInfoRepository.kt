package com.anthony.net.sample.github.client.model.repository.user_info

import com.anthony.net.sample.github.client.dto.common.Error
import com.anthony.net.sample.github.client.dto.login.Repository
import com.anthony.net.sample.github.client.network.Resource
import com.anthony.net.sample.github.client.network.RetrofitBuilder
import com.anthony.net.sample.github.client.service.user_info.UserInfoService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.decodeFromString
import retrofit2.HttpException
import java.io.IOException

class UserInfoRepository(private val userInfoService: UserInfoService) {

    suspend fun getRepositories(
        loginName: String
    ): Flow<Resource<List<Repository>>> = flow {

        try {

            val data = userInfoService.getRepositories(loginName)

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