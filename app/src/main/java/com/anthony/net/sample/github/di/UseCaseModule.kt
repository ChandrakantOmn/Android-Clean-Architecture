package com.anthony.net.sample.github.di


import UserInfoUseCase
import com.anthony.net.sample.github.domain.usecase.login.LoginUseCase
import com.anthony.net.sample.github.domain.usecase.user_info.CollaboratorsUseCase
import com.anthony.net.sample.github.domain.usecase.user_info.CommitsUseCase
import org.koin.dsl.module

val useCaseModule = module {

    single { LoginUseCase(get()) }

    single { CommitsUseCase(get()) }

    single { CollaboratorsUseCase(get()) }

    single { UserInfoUseCase(get()) }

}