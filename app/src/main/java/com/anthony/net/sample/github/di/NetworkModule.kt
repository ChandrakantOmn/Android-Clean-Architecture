package com.anthony.net.sample.github.di

import com.anthony.net.sample.github.client.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import java.io.IOException
import java.util.concurrent.TimeUnit

val networkModule = module {

    single {
        OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            })
            .addInterceptor(RedirectInterceptor())
            .build()
    }

    single<Retrofit> {

        val contentType = "application/json".toMediaType()

        val json = Json {
            isLenient = true
            ignoreUnknownKeys = true
        }

        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(get())
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

}

class RedirectInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest = request.newBuilder()
            .header("Accept", "application/vnd.github+json")
            .header("X-GitHub-Api-Version", "2022-11-28")
            .method(request.method, request.body).build()
        return chain.proceed(newRequest)
    }
}