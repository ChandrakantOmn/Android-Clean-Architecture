package com.anthony.net.sample.github.domain.entity.user_info

import kotlinx.serialization.Serializable

@Serializable
data class Commit(
   val nodeId: String,
   val userName: String,
   val date: String,
   val message: String
) : java.io.Serializable


