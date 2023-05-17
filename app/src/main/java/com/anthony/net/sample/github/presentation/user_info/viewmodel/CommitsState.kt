package com.anthony.net.sample.github.presentation.user_info.viewmodel

import com.anthony.net.sample.github.domain.model.user_info.Commit


sealed class CommitsState {
    data class Success(val commits: List<Commit>?) : CommitsState()
    data class Error(val errorMessage: String?) : CommitsState()
    object Loading : CommitsState()

}