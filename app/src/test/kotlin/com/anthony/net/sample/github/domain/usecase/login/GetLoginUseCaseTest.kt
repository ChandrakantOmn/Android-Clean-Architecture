package com.anthony.net.sample.github.domain.usecase.login

import com.anthony.net.sample.github.data.remote.network.Resource
import com.anthony.net.sample.github.domain.model.common.User
import com.anthony.net.sample.github.domain.repository.login.LoginRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class GetLoginUseCaseTest {
	
	@Mock
	private lateinit var loginRepository: LoginRepository
	
	private lateinit var getLoginUseCase: GetLoginUseCase
	
	@Before
	fun setUp() {
		MockitoAnnotations.openMocks(this)
		getLoginUseCase = GetLoginUseCase(loginRepository)
	}
	
	
	/**Should return an error resource when the username is not found*/
	@Test
	fun invokeWhenUsernameIsNotFound() = runTest {
		val userName = "non_existing_user"
		val error = "User not found"
		val expected = Resource.Error(error)
		
		coEvery { loginRepository.getUser(userName) } coAnswers { flowOf(expected) }
		
		val result = getLoginUseCase(userName).first()
		
		coVerify { loginRepository.getUser(userName) }
		assertEquals(expected, result)
	}
	
	/**Should return a user resource when the username is valid*/
	
// ...
	
	@Test
	fun invokeWhenUsernameIsValid1() = runTest {
		val user = User(
			1,
			"john_doe",
			"https://example.com/avatar.jpg",
			"john.doe@example.com",
			"https://example.com/events",
			10,
			"https://example.com/followers",
			20,
			"https://example.com/following",
			"https://example.com/gists",
			"123456",
			"https://example.com",
			"https://example.com/orgs",
			5,
			15,
			"https://example.com/received_events",
			"https://example.com/repos",
			false,
			"https://example.com/starred",
			"https://example.com/subscriptions",
			"johndoe",
			"https://example.com"
		)
		
		val flow = flow {
			emit(Resource.Loading)
			emit(Resource.Success(user))
		}
		`when`(loginRepository.getUser("john_doe")).thenReturn(flow)
		
		val result = getLoginUseCase("john_doe").first()
		
	//	verify(loginRepository, times(1)).getUser("john_doe")
		coVerify { loginRepository.getUser("john_doe") }
		assertEquals(Resource.Success(user), result)
	}
	
	
	
	@Test
	fun invokeWhenUsernameIsValid3() = runTest {
		val user = User(
			1,
			"john_doe",
			"https://example.com/avatar.jpg",
			"john.doe@example.com",
			"https://example.com/events",
			10,
			"https://example.com/followers",
			20,
			"https://example.com/following",
			"https://example.com/gists",
			"123456",
			"https://example.com",
			"https://example.com/orgs",
			5,
			15,
			"https://example.com/received_events",
			"https://example.com/repos",
			false,
			"https://example.com/starred",
			"https://example.com/subscriptions",
			"johndoe",
			"https://example.com"
		)
		
		val flow = flow {
			emit(Resource.Loading)
			emit(Resource.Success(user))
		}
		`when`(loginRepository.getUser("john_doe")).thenReturn(flow)
		
		val result = getLoginUseCase("john_doe").toList()
		
		coVerify { loginRepository.getUser("john_doe") }
		assertEquals(2, result.size)
		assertEquals(Resource.Loading, result[0])
		assertEquals(Resource.Success(user), result[1])
	}
	
}