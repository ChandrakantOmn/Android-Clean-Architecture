package com.anthony.net.sample.github.presentation.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.anthony.net.sample.github.data.remote.Resource
import com.anthony.net.sample.github.domain.usecase.login.LoginUseCase
import com.anthony.net.sample.github.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUseCase: LoginUseCase) : BaseViewModel() {

    private val _onUser by lazy { MutableLiveData<LoginState>() }

    val onUser: LiveData<LoginState> = _onUser

    fun getUser(userName: String) {
        /*viewModelScope是一个綁定到當前viewModel的作用域  當ViewModel被清除時會自動取消该作用域，所以不用擔心oom*/
        viewModelScope.launch {

            loginUseCase.getUser(userName).collect { result ->

                when (result) {

                    is Resource.Success -> _onUser.value = LoginState(result.data)

                    is Resource.Error -> _onUser.value = LoginState(null, result.errorMessage)

                }

            }

        }
    }
}