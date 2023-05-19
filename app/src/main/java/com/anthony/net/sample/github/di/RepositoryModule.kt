package com.anthony.net.sample.github.di


import com.anthony.net.sample.github.data.repository.login.LoginRepositoryImpl
import com.anthony.net.sample.github.data.repository.user_info.CollaboratorsRepositoryImpl
import com.anthony.net.sample.github.data.repository.user_info.CommitsRepositoryImpl
import com.anthony.net.sample.github.data.repository.user_info.UserInfoRepositoryImpl
import com.anthony.net.sample.github.domain.repository.login.LoginRepository
import com.anthony.net.sample.github.domain.repository.user_info.CollaboratorsRepository
import com.anthony.net.sample.github.domain.repository.user_info.CommitsRepository
import com.anthony.net.sample.github.domain.repository.user_info.UserInfoRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<LoginRepository>(createdAtStart = false) { LoginRepositoryImpl(get()) }

    single<CommitsRepository>(createdAtStart = false) { CommitsRepositoryImpl(get()) }

    single<CollaboratorsRepository>(createdAtStart = false) { CollaboratorsRepositoryImpl(get()) }

    single<UserInfoRepository>(createdAtStart = false) { UserInfoRepositoryImpl(get()) }

}