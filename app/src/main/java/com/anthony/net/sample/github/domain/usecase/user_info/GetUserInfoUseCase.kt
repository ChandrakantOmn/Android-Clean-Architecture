import com.anthony.net.sample.github.data.remote.Resource
import com.anthony.net.sample.github.data.repository.user_info.UserInfoRepository
import com.anthony.net.sample.github.domain.entity.login.Repository
import kotlinx.coroutines.flow.Flow

class GetUserInfoUseCase(private val userInfoRepository: UserInfoRepository) {

    suspend operator fun invoke(
        loginName: String
    ): Flow<Resource<List<Repository>>> {
        return userInfoRepository.getRepositories(loginName)
    }

}