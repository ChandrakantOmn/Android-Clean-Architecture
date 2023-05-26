package com.anthony.net.sample.github.data.repository.login

import com.anthony.net.sample.github.data.remote.model.common.UserDto
import com.anthony.net.sample.github.data.remote.network.Resource
import com.anthony.net.sample.github.data.remote.service.login.LoginService
import com.anthony.net.sample.github.domain.model.common.User
import com.anthony.net.sample.github.domain.repository.login.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LoginRepositoryImpl(private val loginService: LoginService) : LoginRepository {
    override suspend fun getUser(userName: String): Flow<Resource<User>> = flow {
        emit(Resource.Loading)
        val originData = loginService.getUser(userName)
        val dataMapping = mappingOriginDataToUser(originData)
        emit(Resource.Success(dataMapping))
    }.catch {
        emit(Resource.Error(it.localizedMessage ?: "An unknown error occurred"))
    }.flowOn(Dispatchers.IO)


    private fun mappingOriginDataToUser(originData: UserDto): User {
        return User(
            id = originData.id,
            login = originData.login,
            avatar_url = originData.avatar_url,
            email = originData.email,
            events_url = originData.events_url,
            followers = originData.followers,
            followers_url = originData.followers_url,
            following = originData.following,
            following_url = originData.following_url,
            gists_url = originData.gists_url,
            gravatar_id = originData.gravatar_id,
            html_url = originData.html_url,
            organizations_url = originData.organizations_url,
            public_gists = originData.public_gists,
            public_repos = originData.public_repos,
            received_events_url = originData.received_events_url,
            repos_url = originData.repos_url,
            site_admin = originData.site_admin,
            starred_url = originData.starred_url,
            subscriptions_url = originData.subscriptions_url,
            twitter_username = originData.twitter_username,
            url = originData.url
        )
    }

}