package com.anthony.net.sample.github.di


import com.anthony.net.sample.github.data.remote.RetrofitBuilder.createService
import com.anthony.net.sample.github.data.remote.api.login.LoginApi
import com.anthony.net.sample.github.data.remote.api.user_info.CollaboratorsApi
import com.anthony.net.sample.github.data.remote.api.user_info.CommitsApi
import com.anthony.net.sample.github.data.remote.api.user_info.UserInfoApi
import org.koin.dsl.module

val serviceModule = module {

    factory<LoginApi> { createService() }

    factory<CommitsApi> { createService() }

    factory<CollaboratorsApi> { createService() }

    factory<UserInfoApi> { createService() }

}