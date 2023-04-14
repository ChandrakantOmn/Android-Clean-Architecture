package com.anthony.net.sample.github.presentation.user_info.viewmodel

import com.anthony.net.sample.github.data.remote.dto.user_info.Commit

data class CommitsState(
    val commits: List<Commit>? = null,
    val error: String? = null
)