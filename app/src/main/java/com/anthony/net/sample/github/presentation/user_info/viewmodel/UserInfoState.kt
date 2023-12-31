package com.anthony.net.sample.github.presentation.user_info.viewmodel

import com.anthony.net.sample.github.domain.model.login.Repository

sealed class UserInfoState {
    data class Success(val repositories: List<Repository>?) : UserInfoState()
    data class Error(val errorMessage: String?) : UserInfoState()
    object Loading : UserInfoState()
}