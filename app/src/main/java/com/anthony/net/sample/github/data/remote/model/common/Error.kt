package com.anthony.net.sample.github.data.remote.model.common

import kotlinx.serialization.Serializable

@Serializable
data class Error(
    val documentation_url: String,
    val message: String
): java.io.Serializable