package com.anthony.net.sample.github.client.dto.common

import kotlinx.serialization.Serializable

@Serializable
data class License(
    val key: String,
    val name: String,
    val spdx_id: String,
    val url: String? = null,
    val node_id: String,
): java.io.Serializable