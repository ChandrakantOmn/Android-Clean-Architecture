import com.anthony.net.sample.github.data.remote.Resource
import com.anthony.net.sample.github.data.remote.dto.login.Repository
import com.anthony.net.sample.github.domain.repository.user_info.UserInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserInfoUseCase(private val userInfoRepository: UserInfoRepository) {

    suspend fun getRepositories(
        loginName: String
    ): Flow<Resource<List<Repository>>> = flow {
        val result = userInfoRepository.getRepositories(loginName)
        emit(result)
    }

}