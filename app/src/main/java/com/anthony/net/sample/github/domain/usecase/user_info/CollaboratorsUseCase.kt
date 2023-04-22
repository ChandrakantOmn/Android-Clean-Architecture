package com.anthony.net.sample.github.domain.usecase.user_info


import com.anthony.net.sample.github.data.remote.Resource
import com.anthony.net.sample.github.data.remote.dto.user_info.Collaborator
import com.anthony.net.sample.github.domain.repository.user_info.CollaboratorsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CollaboratorsUseCase(private val collaboratorsRepository: CollaboratorsRepository) {

    suspend fun getCollaborators(
        owner: String, repo: String
    ): Flow<Resource<List<Collaborator>>> = flow {
        val result = collaboratorsRepository.getCollaborators(
            owner, repo
        )
        emit(result)
    }

}