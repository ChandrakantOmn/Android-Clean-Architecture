package com.anthony.net.sample.github.presentation.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.anthony.net.sample.github.data.remote.Resource
import com.anthony.net.sample.github.data.remote.dto.common.User
import com.anthony.net.sample.github.domain.usecase.login.LoginUseCase
import com.anthony.net.sample.github.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUseCase: LoginUseCase) : BaseViewModel() {

    private val _onUser by lazy { MutableLiveData<Resource<User>>() }

    val onUser: LiveData<Resource<User>> = _onUser

    fun getUser(userName: String) {
        /*viewModelScope是一个綁定到當前viewModel的作用域  當ViewModel被清除時會自動取消该作用域，所以不用擔心oom*/
        viewModelScope.launch {

            loginUseCase.getUser(userName).collect { resource ->

                _onUser.value = resource

            }

        }
    }
}