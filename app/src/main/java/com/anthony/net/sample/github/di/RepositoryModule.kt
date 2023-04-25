package com.anthony.net.sample.github.di


import com.anthony.net.sample.github.data.repository.login.LoginRepository
import com.anthony.net.sample.github.data.repository.login.LoginRepositoryImpl
import com.anthony.net.sample.github.data.repository.user_info.*
import org.koin.dsl.module

val repositoryModule = module {

    single<LoginRepository> { LoginRepositoryImpl(get()) }

    single<CommitsRepository> { CommitsRepositoryImpl(get()) }

    single<CollaboratorsRepository> { CollaboratorsRepositoryImpl(get()) }

    single<UserInfoRepository> { UserInfoRepositoryImpl(get()) }

}