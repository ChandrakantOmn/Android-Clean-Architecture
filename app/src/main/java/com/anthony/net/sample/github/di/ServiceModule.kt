package com.anthony.net.sample.github.di


import com.anthony.net.sample.github.data.remote.RetrofitBuilder.createService
import com.anthony.net.sample.github.data.remote.service.login.LoginService
import com.anthony.net.sample.github.data.remote.service.user_info.CollaboratorsService
import com.anthony.net.sample.github.data.remote.service.user_info.CommitsService
import com.anthony.net.sample.github.data.remote.service.user_info.UserInfoService
import org.koin.dsl.module

val serviceModule = module {

    single<LoginService> { createService() }

    single<CommitsService> { createService() }

    single<CollaboratorsService> { createService() }

    single<UserInfoService> { createService() }

}