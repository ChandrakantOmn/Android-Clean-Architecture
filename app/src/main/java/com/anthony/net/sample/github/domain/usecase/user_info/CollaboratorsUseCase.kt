package com.anthony.net.sample.github.domain.usecase.user_info


import com.anthony.net.sample.github.data.remote.Resource
import com.anthony.net.sample.github.data.remote.dto.user_info.Collaborator
import com.anthony.net.sample.github.data.remote.handleException
import com.anthony.net.sample.github.data.repository.user_info.CollaboratorsRepositoryImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CollaboratorsUseCase(private val collaboratorsRepository: CollaboratorsRepositoryImpl) {

    suspend fun getCollaborators(
        owner: String, repo: String
    ): Flow<Resource<List<Collaborator>>> = flow {

        try {

            val data = collaboratorsRepository.getCollaborators(
                owner, repo
            )

            emit(Resource.Success(data))

        } catch (e: Exception) {
            emit(handleException(e))
        }

    }

}