package com.anthony.net.sample.github.di


import com.anthony.net.sample.github.data.remote.service.login.LoginService
import com.anthony.net.sample.github.data.remote.service.user_info.CollaboratorsService
import com.anthony.net.sample.github.data.remote.service.user_info.CommitsService
import com.anthony.net.sample.github.data.remote.service.user_info.UserInfoService
import org.koin.dsl.module
import retrofit2.Retrofit

val serviceModule = module {

    single(createdAtStart = false) { get<Retrofit>().create(LoginService::class.java) }

    single(createdAtStart = false) { get<Retrofit>().create(CommitsService::class.java) }

    single(createdAtStart = false) { get<Retrofit>().create(CollaboratorsService::class.java) }

    single(createdAtStart = false) { get<Retrofit>().create(UserInfoService::class.java) }

}