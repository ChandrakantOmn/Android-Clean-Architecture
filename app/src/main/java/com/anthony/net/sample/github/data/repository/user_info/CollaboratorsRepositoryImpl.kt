package com.anthony.net.sample.github.data.repository.user_info

import com.anthony.net.sample.github.data.remote.model.user_info.CollaboratorDto
import com.anthony.net.sample.github.data.remote.network.Resource
import com.anthony.net.sample.github.data.remote.service.user_info.CollaboratorsService
import com.anthony.net.sample.github.domain.model.user_info.Collaborator
import com.anthony.net.sample.github.domain.repository.user_info.CollaboratorsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CollaboratorsRepositoryImpl(private val collaboratorsService: CollaboratorsService) :
    CollaboratorsRepository {
    override suspend fun getCollaborators(
        owner: String,
        repo: String
    ): Flow<Resource<List<Collaborator>>> = flow {
        emit(Resource.Loading)
        val originData = collaboratorsService.getCollaborators(owner, repo)
        val dataMapping =
            originData.map { collaboratorDto -> mapCollaboratorDtoToCollaborator(collaboratorDto) }
        emit(Resource.Success(dataMapping))
    }.catch {
        emit(Resource.Error(it.localizedMessage ?: "An unknown error occurred"))
    }.flowOn(Dispatchers.IO)

}

private fun mapCollaboratorDtoToCollaborator(collaboratorDto: CollaboratorDto): Collaborator {

    return Collaborator(
        id = collaboratorDto.id,
        avatarUrl = collaboratorDto.avatar_url,
        collaboratorName = collaboratorDto.login
    )

}