package com.anthony.net.sample.github.di


import UserInfoUseCase
import com.anthony.net.sample.github.data.repository.login.LoginRepositoryImpl
import com.anthony.net.sample.github.data.repository.user_info.CollaboratorsRepositoryImpl
import com.anthony.net.sample.github.data.repository.user_info.CommitsRepositoryImpl
import com.anthony.net.sample.github.data.repository.user_info.UserInfoRepositoryImpl
import com.anthony.net.sample.github.domain.usecase.login.LoginUseCase
import com.anthony.net.sample.github.domain.usecase.user_info.CollaboratorsUseCase
import com.anthony.net.sample.github.domain.usecase.user_info.CommitsUseCase
import org.koin.dsl.module

val useCaseModule = module {

    single { LoginUseCase(LoginRepositoryImpl(get())) }

    single { CommitsUseCase(CommitsRepositoryImpl(get())) }

    single { CollaboratorsUseCase(CollaboratorsRepositoryImpl(get())) }

    single { UserInfoUseCase(UserInfoRepositoryImpl(get())) }

}