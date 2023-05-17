package com.anthony.net.sample.github.data.remote.network


import com.anthony.net.sample.github.client.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 *  是一個用於構建和設置 Retrofit 實例的 Kotlin 物件
 *  RetrofitBuilder 可以簡化 Retrofit 服務實例的創建，並確保所有服務使用相同的基本配置。此外，它還允許集中管理 OkHttpClient 的配置，包括攔截器和超時設置。
 */
object RetrofitBuilder {

    /**
     * 使用 Kotlinx Serialization 庫定義一個 JSON 解析器，允許"寬鬆的解析"和"忽略未知鍵"。
     */
    val json = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

    /**
     * createOkHttpClient：創建一個配置了攔截器、超時和重試策略的 OkHttpClient。
     * 配置了 HttpLoggingInterceptor 以記錄 HTTP 請求和響應的詳細信息。
     * 啟用了連接失敗時的重試。
     * 設置了連接超時和讀取超時。
     * 禁用了 HTTP 重定向和 SSL 重定向。
     * 添加了 RedirectInterceptor 來處理重定向。
     */
    fun createOkHttpClient(): OkHttpClient {

        val httpClient = OkHttpClient.Builder()

        val httpLoggingInterceptor = HttpLoggingInterceptor()

        httpLoggingInterceptor.level =
            HttpLoggingInterceptor.Level.BODY

        httpClient
            .retryOnConnectionFailure(true)//默認重試一次，需要重試n次的話需要實現攔截器
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .followRedirects(false)
            .followSslRedirects(false)
            .addInterceptor(httpLoggingInterceptor)

        httpClient.addInterceptor(RedirectInterceptor())


        return httpClient.build()

    }

    /**
     * 一個泛型函數，用於創建具體的 Retrofit 服務。它根據泛型參數 T 生成 Retrofit 實例，並將 JSON 轉換工廠和 OkHttpClient 添加到 Retrofit.Builder 中。
     */
    inline fun <reified T> createService(): T {
        val contentType = "application/json".toMediaType()
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(createOkHttpClient())
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
        return retrofit.create(T::class.java)
    }


    private fun createResponse(chain: Interceptor.Chain, request: Request): Response {

        return chain.proceed(
            request.newBuilder()
                .header("Accept", "application/vnd.github+json")
                .header("X-GitHub-Api-Version", "2022-11-28")
                .method(request.method, request.body).build()
        )

    }

    /**
     * 一個自定義攔截器，用於處理重定向。在這個實現中，它簡單地返回原始請求的響應。註釋掉的部分可用於檢查響應狀態碼並拋出異常。
     */
    class RedirectInterceptor : Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {

            val request = chain.request()
            val response = createResponse(chain, request)

//            when (response.code) {
//                in 400..900 -> {
//                    throw IOException(response.toString())
//                }
//            }

            return response

        }
    }
}

