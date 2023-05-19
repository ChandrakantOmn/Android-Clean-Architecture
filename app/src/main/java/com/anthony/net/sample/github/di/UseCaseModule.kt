package com.anthony.net.sample.github.di


import GetUserInfoUseCase
import com.anthony.net.sample.github.domain.usecase.login.GetLoginUseCase
import com.anthony.net.sample.github.domain.usecase.user_info.GetCollaboratorsUseCase
import com.anthony.net.sample.github.domain.usecase.user_info.GetCommitsUseCase
import org.koin.dsl.module

val useCaseModule = module {

    single(createdAtStart = false) { GetLoginUseCase(get()) }

    single(createdAtStart = false) { GetCommitsUseCase(get()) }

    single(createdAtStart = false) { GetCollaboratorsUseCase(get()) }

    single(createdAtStart = false) { GetUserInfoUseCase(get()) }

}