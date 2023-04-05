import com.anthony.net.sample.github.data.remote.Resource
import com.anthony.net.sample.github.data.remote.dto.login.Repository
import com.anthony.net.sample.github.data.remote.handleException
import com.anthony.net.sample.github.data.repository.user_info.UserInfoRepositoryImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserInfoUseCase(private val userInfoRepository: UserInfoRepositoryImpl) {

    suspend fun getRepositories(
        loginName: String
    ): Flow<Resource<List<Repository>>> = flow {

        try {

            val data = userInfoRepository.getRepositories(loginName)

            emit(Resource.Success(data))

        } catch (e: Exception) {
            emit(handleException(e))
        }

    }

}