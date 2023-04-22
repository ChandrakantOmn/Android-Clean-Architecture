package com.anthony.net.sample.github.data.remote

import kotlinx.serialization.decodeFromString
import retrofit2.HttpException
import java.io.IOException

/**
 *Resource是一個密封類。
 *它用於封裝API請求的結果，其中T表示結果的資料類型。Resource有兩個子類：
 *Success：表示請求成功。包含成功獲取的資料。
 *Error：表示請求失敗。包含錯誤消息。
 **/
sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val errorMessage: String) : Resource<Nothing>()
}

/**
 *
 * handleException函數是一個泛型函數，用於處理API請求過程中的異常。它接收一個Exception參數，根據異常類型返回對應的Resource.Error實例：
 * 如果異常是HttpException（通常表示網路連接問題），則返回一個帶有提示消息的Resource.Error實例。
 * 如果異常是IOException（表示輸入/輸出操作異常），則返回一個帶有提示消息的Resource.Error實例。
 * 對於其他異常類型，返回一個帶有提示消息的Resource.Error實例。
 */
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
            Resource.Error("An unexpected error occurred")
        }
        else -> {
            Resource.Error("An unexpected error occurred")
        }
    }
}