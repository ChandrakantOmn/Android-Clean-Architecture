package com.anthony.net.sample.github.data.remote.service.login

import com.anthony.net.sample.github.data.remote.model.common.UserDto
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class LoginServiceTest {
	@Mock
	lateinit var loginService: LoginService
	
	@Before
	fun setUp() {
		MockitoAnnotations.openMocks(this)
		
	}
	
	/**Should throw an exception when the username is not found*/
	
	@Test
	fun getUserWhenUsernameIsNotFoundThenThrowException() = runTest {
		val userName = "nonExistingUser"
		val errorResponseBody = ResponseBody.create("application/json".toMediaTypeOrNull(), "User not found")
		val errorResponse = Response.error<UserDto>(404, errorResponseBody)
		`when`(loginService.getUser(userName)).thenThrow(HttpException(errorResponse))
		
		//`when`(loginService.getUser(userName)).thenReturn(errorResponse)
		
		val exception = assertThrows(IOException::class.java) {
			runBlocking { loginService.getUser(userName) }
		}
		
		assertEquals("User not found", exception.message)
	}
	
	
	/**Should return the user when the username is valid*/
	@Test
	fun getUserWhenUsernameIsValid() = runTest {
		val userName = "john_doe"
		val userDto = UserDto(
			"https://avatars.githubusercontent.com/u/123456?v=4",
			"Software Engineer",
			"https://johndoe.com",
			"ABC Inc.",
			"2021-10-01T10:00:00Z",
			"john.doe@example.com",
			"https://api.github.com/users/john_doe/events{/privacy}",
			10,
			"https://api.github.com/users/john_doe/followers",
			20,
			"https://api.github.com/users/john_doe/following{/other_user}",
			"https://api.github.com/users/john_doe/gists{/gist_id}",
			"123456",
			true,
			"https://github.com/john_doe",
			123456,
			"San Francisco, CA",
			"john_doe",
			"John Doe",
			"MDQ6VXNlcjE=",
			"https://api.github.com/users/john_doe/orgs",
			5,
			10,
			"https://api.github.com/users/john_doe/received_events",
			"https://api.github.com/users/john_doe/repos",
			false,
			"https://api.github.com/users/john_doe/starred{/owner}{/repo}",
			"https://api.github.com/users/john_doe/subscriptions",
			"johndoe",
			"User",
			"2021-10-01T10:00:00Z",
			"https://api.github.com/users/john_doe"
		)
		
		`when`(loginService.getUser(userName)).thenReturn(userDto)
		
		val result = loginService.getUser(userName)
		
		assertNotNull(result)
		assertEquals(userDto, result)
		assertEquals(userDto, result)
		//assertEquals(200, result.code())
	}
	
}