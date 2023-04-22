package com.anthony.net.sample.github.data.repository.user_info

import com.anthony.net.sample.github.data.remote.Resource
import com.anthony.net.sample.github.data.remote.dto.user_info.Collaborator
import com.anthony.net.sample.github.data.remote.handleException
import com.anthony.net.sample.github.data.remote.service.user_info.CollaboratorsService
import com.anthony.net.sample.github.domain.repository.user_info.CollaboratorsRepository

class CollaboratorsRepositoryImpl(private val collaboratorsService: CollaboratorsService) :
    CollaboratorsRepository {

    override suspend fun getCollaborators(
        owner: String,
        repo: String
    ): Resource<List<Collaborator>> {
        return try {
            val data = collaboratorsService.getCollaborators(owner, repo)
            Resource.Success(data)
        } catch (e: Exception) {
            handleException(e)
        }
    }

}