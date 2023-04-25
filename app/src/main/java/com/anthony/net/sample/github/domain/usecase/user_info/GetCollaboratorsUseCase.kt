package com.anthony.net.sample.github.domain.usecase.user_info


import com.anthony.net.sample.github.data.remote.Resource
import com.anthony.net.sample.github.data.repository.user_info.CollaboratorsRepository
import com.anthony.net.sample.github.domain.entity.user_info.Collaborator
import kotlinx.coroutines.flow.Flow

class GetCollaboratorsUseCase(private val collaboratorsRepository: CollaboratorsRepository) {

    suspend operator fun invoke(
        owner: String, repo: String
    ): Flow<Resource<List<Collaborator>>> {
        return collaboratorsRepository.getCollaborators(owner, repo)
    }

}