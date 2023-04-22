package com.anthony.net.sample.github.di


import com.anthony.net.sample.github.data.repository.login.LoginRepositoryImpl
import com.anthony.net.sample.github.data.repository.user_info.CollaboratorsRepositoryImpl
import com.anthony.net.sample.github.data.repository.user_info.CommitsRepositoryImpl
import com.anthony.net.sample.github.data.repository.user_info.UserInfoRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    single { LoginRepositoryImpl(get()) }

    single { CommitsRepositoryImpl(get()) }

    single { CollaboratorsRepositoryImpl(get()) }

    single { UserInfoRepositoryImpl(get()) }

}