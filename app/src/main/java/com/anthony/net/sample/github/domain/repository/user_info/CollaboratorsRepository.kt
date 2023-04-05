package com.anthony.net.sample.github.domain.repository.user_info

import com.anthony.net.sample.github.data.remote.dto.user_info.Collaborator

interface CollaboratorsRepository {

    suspend fun getCollaborators(
        owner: String, repo: String
    ): List<Collaborator>


}