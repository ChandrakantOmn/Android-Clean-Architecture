package com.anthony.net.sample.github.data.remote

import kotlinx.serialization.decodeFromString
import retrofit2.HttpException
import java.io.IOException

sealed class Resource<T>(val data: T? = null, val errorMessage: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(errorMessage: String, data: T? = null) : Resource<T>(data, errorMessage)
}

fun <T> handleException(e: Exception): Resource<T> {
    return when (e) {
        is HttpException -> {
            val errorMessage = e.response()?.errorBody()?.let {
                RetrofitBuilder.json.decodeFromString<com.anthony.net.sample.github.data.remote.dto.common.Error>(
                    it.string()
                ).message
            } ?: "An unexpected error occurred"
            Resource.Error(errorMessage)
        }
        is IOException -> {
            Resource.Error("Couldn't reach server. Check your internet connection.")
        }
        else -> {
            Resource.Error("An unexpected error occurred")
        }
    }
}