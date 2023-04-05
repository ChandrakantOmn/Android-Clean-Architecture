package com.anthony.net.sample.github.di


import com.anthony.net.sample.github.presentation.login.viewmodel.LoginViewModel
import com.anthony.net.sample.github.presentation.user_info.viewmodel.CollaboratorsViewModel
import com.anthony.net.sample.github.presentation.user_info.viewmodel.CommitsViewModel
import com.anthony.net.sample.github.presentation.user_info.viewmodel.UserInfoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { LoginViewModel(get()) }

    viewModel { CommitsViewModel(get()) }

    viewModel { CollaboratorsViewModel(get()) }

    viewModel { UserInfoViewModel(get()) }

}