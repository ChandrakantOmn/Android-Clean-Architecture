package com.anthony.net.sample.github.presentation.user_info.viewmodel

import com.anthony.net.sample.github.data.remote.dto.user_info.Collaborator

data class CollaboratorsState(
    val collaborators: List<Collaborator>? = null,
    val error: String? = null
)