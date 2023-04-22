package com.anthony.net.sample.github.presentation.user_info.viewmodel

import com.anthony.net.sample.github.data.remote.dto.user_info.Commit


sealed class CommitsState {
    data class Success(val commits: List<Commit>?) : CommitsState()
    data class Error(val errorMessage: String?) : CommitsState()
}