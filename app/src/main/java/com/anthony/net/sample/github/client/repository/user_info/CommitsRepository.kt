package com.anthony.net.sample.github.client.repository.user_info

import com.anthony.net.sample.github.client.model.common.Error
import com.anthony.net.sample.github.client.model.user_info.Commit
import com.anthony.net.sample.github.client.network.Resource
import com.anthony.net.sample.github.client.network.RetrofitBuilder
import com.anthony.net.sample.github.client.service.user_info.CommitsService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.decodeFromString
import retrofit2.HttpException
import java.io.IOException

class CommitsRepository(private val commitsService: CommitsService) {

    suspend fun getCommits(
        userName: String, repoName: String
    ): Flow<Resource<List<Commit>>> = flow {

        try {

            val data = commitsService.getCommits(userName, repoName)

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