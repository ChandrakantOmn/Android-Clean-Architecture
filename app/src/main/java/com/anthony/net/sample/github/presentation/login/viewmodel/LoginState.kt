package com.anthony.net.sample.github.presentation.login.viewmodel

import com.anthony.net.sample.github.data.remote.dto.common.User

data class LoginState(
    val user: User? = null,
    val error: String? = null
)