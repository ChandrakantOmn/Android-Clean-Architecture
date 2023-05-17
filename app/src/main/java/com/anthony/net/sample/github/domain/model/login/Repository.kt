package com.anthony.net.sample.github.domain.model.login

import kotlinx.serialization.Serializable


@Serializable
data class Repository(
    val id: Int,
    val avatarUrl: String,
    val stargazersCount: Int,
    val forksCount: Int,
    val repositoryOwner: String,
    val repositoryName: String,
    val repositoryDescription: String,
    val repositoryLanguage: String,
) : java.io.Serializable
