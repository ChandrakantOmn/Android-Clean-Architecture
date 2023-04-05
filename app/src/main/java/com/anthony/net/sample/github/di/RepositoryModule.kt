package com.anthony.net.sample.github.di


import com.anthony.net.sample.github.data.repository.login.LoginRepositoryImpl
import com.anthony.net.sample.github.data.repository.user_info.CollaboratorsRepositoryImpl
import com.anthony.net.sample.github.data.repository.user_info.CommitsRepositoryImpl
import com.anthony.net.sample.github.data.repository.user_info.UserInfoRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    factory { LoginRepositoryImpl(get()) }

    factory { CommitsRepositoryImpl(get()) }

    factory { CollaboratorsRepositoryImpl(get()) }

    factory { UserInfoRepositoryImpl(get()) }

}