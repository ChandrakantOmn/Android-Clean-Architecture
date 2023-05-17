package com.anthony.net.sample.github.presentation.login.viewmodel

import com.anthony.net.sample.github.domain.model.common.User

sealed class LoginState {
    data class Success(val user: User?) : LoginState()
    data class Error(val errorMessage: String?) : LoginState()
    object Loading : LoginState()

}