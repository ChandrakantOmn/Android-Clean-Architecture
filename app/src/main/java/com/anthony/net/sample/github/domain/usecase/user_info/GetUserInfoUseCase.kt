import com.anthony.net.sample.github.data.remote.network.Resource
import com.anthony.net.sample.github.domain.model.login.Repository
import com.anthony.net.sample.github.domain.repository.user_info.UserInfoRepository
import kotlinx.coroutines.flow.Flow

class GetUserInfoUseCase(private val userInfoRepository: UserInfoRepository) {

    suspend operator fun invoke(
        loginName: String
    ): Flow<Resource<List<Repository>>> {
        return userInfoRepository.getRepositories(loginName)
    }

}