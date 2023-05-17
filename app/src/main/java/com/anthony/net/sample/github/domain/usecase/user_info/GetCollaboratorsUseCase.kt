package com.anthony.net.sample.github.domain.usecase.user_info


import com.anthony.net.sample.github.data.remote.network.Resource
import com.anthony.net.sample.github.domain.model.user_info.Collaborator
import com.anthony.net.sample.github.domain.repository.user_info.CollaboratorsRepository
import kotlinx.coroutines.flow.Flow

class GetCollaboratorsUseCase(private val collaboratorsRepository: CollaboratorsRepository) {

    suspend operator fun invoke(
        owner: String, repo: String
    ): Flow<Resource<List<Collaborator>>> {
        return collaboratorsRepository.getCollaborators(owner, repo)
    }

}