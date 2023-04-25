package com.anthony.net.sample.github.data.repository.user_info

import com.anthony.net.sample.github.data.remote.Resource
import com.anthony.net.sample.github.domain.entity.user_info.Collaborator
import kotlinx.coroutines.flow.Flow

interface CollaboratorsRepository {

    suspend fun getCollaborators(
        owner: String, repo: String
    ): Flow<Resource<List<Collaborator>>>


}