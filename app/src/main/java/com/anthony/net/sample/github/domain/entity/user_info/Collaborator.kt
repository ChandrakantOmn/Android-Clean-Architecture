package com.anthony.net.sample.github.domain.entity.user_info

import kotlinx.serialization.Serializable


@Serializable
data class Collaborator(
    val id: Int,
    val avatarUrl: String,
    val collaboratorName: String,
) : java.io.Serializable

