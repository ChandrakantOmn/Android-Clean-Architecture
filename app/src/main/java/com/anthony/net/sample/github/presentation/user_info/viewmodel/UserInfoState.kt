package com.anthony.net.sample.github.presentation.user_info.viewmodel

import com.anthony.net.sample.github.data.remote.dto.login.Repository

data class UserInfoState(
    val repositories: List<Repository>? = null,
    val error: String? = null
)