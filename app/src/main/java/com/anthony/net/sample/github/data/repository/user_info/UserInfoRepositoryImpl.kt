package com.anthony.net.sample.github.data.repository.user_info

import com.anthony.net.sample.github.data.remote.model.login.RepositoryDto
import com.anthony.net.sample.github.data.remote.network.Resource
import com.anthony.net.sample.github.data.remote.service.user_info.UserInfoService
import com.anthony.net.sample.github.domain.model.login.Repository
import com.anthony.net.sample.github.domain.repository.user_info.UserInfoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserInfoRepositoryImpl(private val userInfoService: UserInfoService) : UserInfoRepository {
    override suspend fun getRepositories(loginName: String): Flow<Resource<List<Repository>>> =
        flow {
            emit(Resource.Loading)
            val originData = userInfoService.getRepositories(loginName)
            val dataMapping =
                originData.map { repositoryDto -> mapRepositoryDtoToRepository(repositoryDto) }
            emit(Resource.Success(dataMapping))
        }.catch {
            emit(Resource.Error(it.localizedMessage ?: "An unknown error occurred"))
        }.flowOn(Dispatchers.IO)

}

private fun mapRepositoryDtoToRepository(repositoryDto: RepositoryDto): Repository {

    return Repository(
        id = repositoryDto.id,
        avatarUrl = repositoryDto.owner.avatar_url,
        stargazersCount = repositoryDto.stargazers_count,
        forksCount = repositoryDto.forks_count,
        repositoryOwner = repositoryDto.owner.login,
        repositoryName = repositoryDto.name,
        repositoryDescription = repositoryDto.description.orEmpty(),
        repositoryLanguage = repositoryDto.language.orEmpty()
    )

}