package com.anthony.net.sample.github.client.model.repository.user_info

import com.anthony.net.sample.github.client.dto.common.Error
import com.anthony.net.sample.github.client.dto.user_info.Collaborator
import com.anthony.net.sample.github.client.network.Resource
import com.anthony.net.sample.github.client.network.RetrofitBuilder
import com.anthony.net.sample.github.client.service.user_info.CollaboratorsService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.decodeFromString
import retrofit2.HttpException
import java.io.IOException

class CollaboratorsRepository(private val collaboratorsService: CollaboratorsService) {

    suspend fun getCollaborators(
        owner: String, repo: String
    ): Flow<Resource<List<Collaborator>>> = flow {

        try {

            val data = collaboratorsService.getCollaborators(
                owner, repo
            )

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