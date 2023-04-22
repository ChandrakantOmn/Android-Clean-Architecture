package com.anthony.net.sample.github.presentation.login.viewmodel

import com.anthony.net.sample.github.data.remote.dto.common.User

sealed class LoginState {
    data class Success(val user: User?) : LoginState()
    data class Error(val errorMessage: String?) : LoginState()
}