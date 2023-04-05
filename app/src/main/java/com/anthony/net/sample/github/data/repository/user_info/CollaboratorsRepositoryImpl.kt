package com.anthony.net.sample.github.data.repository.user_info

import com.anthony.net.sample.github.data.remote.api.user_info.CollaboratorsApi
import com.anthony.net.sample.github.data.remote.dto.user_info.Collaborator
import com.anthony.net.sample.github.domain.repository.user_info.CollaboratorsRepository

class CollaboratorsRepositoryImpl(private val collaboratorsApi: CollaboratorsApi) :
    CollaboratorsRepository {

    override suspend fun getCollaborators(owner: String, repo: String): List<Collaborator> {

        return collaboratorsApi.getCollaborators(owner, repo)

    }

}