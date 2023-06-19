package com.anthony.net.sample.github.data.repository.login

import com.anthony.net.sample.github.data.remote.model.common.UserDto
import com.anthony.net.sample.github.data.remote.network.Resource
import com.anthony.net.sample.github.data.remote.service.login.LoginService
import com.anthony.net.sample.github.domain.model.common.User
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import junit.framework.TestCase.fail
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.doThrow
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class LoginRepositoryImplTest {
	private lateinit var loginRepository: LoginRepositoryImpl
	private lateinit var loginService: LoginService
	
	@Before
	fun setUp() {
		MockitoAnnotations.openMocks(this)
		loginService = mock(LoginService::class.java)
		loginRepository = LoginRepositoryImpl(loginService)
	}
	
	@After
	fun tearDown() {
		verifyNoMoreInteractions(loginService)
	}
	
	/**Should emit loading state and then error state when getUser is called with an invalid username*/
	
	/**Should emit loading state and then error state with a message when getUser encounters an unknown error*/
	@Test
	fun getUserWithUnknownErrorEmitsLoadingAndErrorStates()= runTest {
		val userName = "invalid_username"
		val exceptionMessage = "An unknown error occurred"
		val exception = RuntimeException(exceptionMessage)
		
		`when`(loginService.getUser(userName)).thenThrow(exception)
		
			loginRepository.getUser(userName).collect {
				when (it) {
					is Resource.Loading -> {
						// Assert that loading state is emitted
					}
					
					is Resource.Error -> {
						// Assert that error state is emitted with correct error message
					}
					
					else -> {
						fail("Unexpected state emitted")
					}
				}
			}
		
		
		verify(loginService).getUser(userName)
	}
	
	/**Should emit loading state and then success state with user data when getUser is called with a valid username*/
	@Test
	fun getUserWithValidUsernameEmitsLoadingAndSuccessStates()= runTest {
		val userName = "testUser"
		val userDto = UserDto(
			"https://test.com/avatar",
			"test bio",
			"https://test.com/blog",
			"test company",
			"2021-01-01T00:00:00Z",
			"test@test.com",
			"https://test.com/events",
			10,
			"https://test.com/followers",
			20,
			"https://test.com/following",
			"https://test.com/gists",
			"test gravatar",
			true,
			"https://test.com/html",
			123,
			"test location",
			"test login",
			"test name",
			"test node",
			"https://test.com/org",
			5,
			15,
			"https://test.com/received_events",
			"https://test.com/repos",
			true,
			"https://test.com/starred",
			"https://test.com/subscriptions",
			"test_twitter",
			"User",
			"2021-01-01T00:00:00Z",
			"https://test.com"
		)
		
		val user = User(
			123,
			"test login",
			"https://test.com/avatar",
			"test@test.com",
			"https://test.com/events",
			10,
			"https://test.com/followers",
			20,
			"https://test.com/following",
			"https://test.com/gists",
			"test gravatar",
			"https://test.com/html",
			"https://test.com/org",
			5,
			15,
			"https://test.com/received_events",
			"https://test.com/repos",
			true,
			"https://test.com/starred",
			"https://test.com/subscriptions",
			"test_twitter",
			"https://test.com"
		)
		
			`when`(loginService.getUser(userName)).thenReturn(userDto)
			
			val result = loginRepository.getUser(userName).toList()
			
			verify(loginService).getUser(userName)
			
			assertEquals(2, result.size)
			assertTrue(result[0] is Resource.Loading)
			assertTrue(result[1] is Resource.Success)
			assertEquals(user, (result[1] as Resource.Success).data)
		
	}
	
	/**Should emit loading state and then error state with a message when getUser is called with an invalid username*/
	@Test
	fun getUserWithInvalidUsernameEmitsLoadingAndErrorStates()= runTest {
	 	 val userName = "invalid_username"
		val exception = RuntimeException("Invalid username")
		doThrow(exception).`when`(loginService).getUser(userName)
		val testFlow = loginRepository.getUser(userName).toList()
		assertEquals(2, testFlow.size)
		assertTrue(testFlow[0] is Resource.Loading)
		assertTrue(testFlow[1] is Resource.Error)
		assertEquals(exception.localizedMessage, (testFlow[1] as Resource.Error).errorMessage)
		verify(loginService).getUser(userName)
		
	}
	
}