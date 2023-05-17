package com.anthony.net.sample.github.domain.model.user_info

import kotlinx.serialization.Serializable


@Serializable
data class Collaborator(
    val id: Int,
    val avatarUrl: String,
    val collaboratorName: String,
) : java.io.Serializable

